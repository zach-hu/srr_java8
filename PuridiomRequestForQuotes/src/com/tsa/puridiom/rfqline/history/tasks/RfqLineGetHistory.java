/*
 * Created on Sept 29, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqline.history.tasks.RfqLineGetHistory.java
 *
 */
package com.tsa.puridiom.rfqline.history.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.historylog.tasks.RfqLineSetupHistoryValues;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RfqLineGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            RfqLine newLine	= (RfqLine)incomingRequest.get("newHistoryRfqLine");
            RfqHeader header = (RfqHeader)incomingRequest.get("rfqHeader");

            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Rfq Line was not found!");
            }

            String organizationId = (String) incomingRequest.get("organizationId");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            String sAutoReleased = (String)incomingRequest.get("autoReleased");
            String historyreason = (String)incomingRequest.get("historyreason");
            if(historyreason == null){	historyreason = "";	}
            boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");

            RfqLineSetupHistoryValues historyBuild = new RfqLineSetupHistoryValues();
            historyBuild.setReason(historyreason);
            historyBuild.setOrganizationId(organizationId);
            historyBuild.setAutoReleased(autoReleased);
            historyBuild.setNextUser(forwardedTo);
            historyBuild.setIpAddress((String)incomingRequest.get("ipAddress"));
            
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
