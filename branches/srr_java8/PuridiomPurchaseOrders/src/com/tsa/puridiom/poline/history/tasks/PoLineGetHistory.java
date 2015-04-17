/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.poheader.history.tasks.PoLineGetHistory.java
 *
 */
package com.tsa.puridiom.poline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.historylog.tasks.PoLineSetupHistoryValues;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import java.math.BigDecimal;

public class PoLineGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            PoLine newLine	= (PoLine)incomingRequest.get("newHistoryPoLine");
            PoHeader header = (PoHeader)incomingRequest.get("poHeader");

            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Po Line was not found!");
            }

            String organizationId = (String)incomingRequest.get("organizationId");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            String sAutoReleased = (String)incomingRequest.get("autoReleased");
            String historyreason = (String)incomingRequest.get("historyreason");
            String writehistory = (String)incomingRequest.get("writehistory");
            String originalQty = (String) incomingRequest.get("originalQuantity");
            String originalPrice = (String) incomingRequest.get("originalPrice");
            if(historyreason == null){	historyreason = "";	}
            if (writehistory == null) {	writehistory = "N";	}
            if (HiltonUtility.isEmpty(originalQty)) {	originalQty = "-1";	}
            if (HiltonUtility.isEmpty(originalPrice)) {	originalPrice = "-1";	}
            BigDecimal bdOriginalQty = new BigDecimal(originalQty);
            BigDecimal bdOriginalPrice = new BigDecimal(originalPrice);

            boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");

            PoLineSetupHistoryValues historyBuild = new PoLineSetupHistoryValues();

            if( incomingRequest.containsKey("PoLine_requisitionNumber") )
            {
            	historyBuild.setRequisitionNumber( (String) incomingRequest.get("PoLine_requisitionNumber") );
            }

            historyBuild.setReason(historyreason);
            historyBuild.setOrganizationId(organizationId);
            historyBuild.setAutoReleased(autoReleased);
            historyBuild.setNextUser(forwardedTo);
            historyBuild.setWriteHistory(writehistory);
            historyBuild.setOriginalQty(bdOriginalQty);
            historyBuild.setOriginalPrice(bdOriginalPrice);
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));

            history = historyBuild.getLineHistoryLog(newLine, header);

            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return history;
    }
}
