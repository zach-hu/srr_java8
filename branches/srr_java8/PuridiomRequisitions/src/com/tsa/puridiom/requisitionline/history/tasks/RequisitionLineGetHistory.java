/*
 * Created on Jul 21, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisitionheader.history.tasks.RequisitionLineGetHistory.java
 *
 */
package com.tsa.puridiom.requisitionline.history.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.requisitionline.history.RequisitionLineSetupHistoryValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.common.documents.*;
import com.tsa.puridiom.common.utility.HiltonUtility;

public class RequisitionLineGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionLine newLine	= (RequisitionLine)incomingRequest.get("newHistoryRequisitionLine");
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Requisition Line was not found!");
            }

            RequisitionLineSetupHistoryValues historyBuild = new RequisitionLineSetupHistoryValues();
            historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
            historyBuild.setUserId((String)incomingRequest.get("userId"));
            historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
            historyBuild.setPoLine((PoLine)incomingRequest.get("PoLine"));
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            historyBuild.setTimeZone((String)incomingRequest.get("userTimeZone"));

            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            if (forwardedTo == null)
            {
                forwardedTo = "";
            }
            else
            {
                forwardedTo = UserManager.getInstance().getUser(
                        (String)incomingRequest.get("organizationId"), forwardedTo).getDisplayName();
            }
            historyBuild.setForwardedTo(forwardedTo);

            String rejectedBy = (String)incomingRequest.get("rejectedBy");
            if (rejectedBy == null)
            {
                rejectedBy = "";
            }
            else
            {
                rejectedBy = UserManager.getInstance().getUser(
                        (String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
            }
            historyBuild.setRejectedBy(rejectedBy);

            String writehistory = (String)incomingRequest.get("writehistory");
            String originalQty = (String) incomingRequest.get("originalQuantity");
            String originalPrice = (String) incomingRequest.get("originalPrice");
            if (writehistory == null) {	writehistory = "N";	}
            if (HiltonUtility.isEmpty(originalQty)) {	originalQty = "-1";	}
            if (HiltonUtility.isEmpty(originalPrice)) {	originalPrice = "-1";	}
            BigDecimal bdOriginalQty = new BigDecimal(originalQty);
            BigDecimal bdOriginalPrice = new BigDecimal(originalPrice);
            historyBuild.setWriteHistory(writehistory);
            historyBuild.setOriginalQty(bdOriginalQty);
            historyBuild.setOriginalPrice(bdOriginalPrice);

            history = historyBuild.getLineHistoryLog(newLine, header);

            if (incomingRequest.containsKey("RequisitionLine_noteCancel") && ( DocumentStatus.CANCELLED.compareTo(history.getStatus()) == 0  || DocumentStatus.CLOSED.compareTo(history.getStatus()) == 0 ) ) {
            	history.setDescription(history.getDescription() + " Reason:" + incomingRequest.get("RequisitionLine_noteCancel"));
            }

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
