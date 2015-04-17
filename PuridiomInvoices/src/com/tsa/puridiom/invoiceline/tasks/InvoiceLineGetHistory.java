/*
 * Created on Sept 29, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqline.history.tasks.RfqLineGetHistory.java
 *
 */
package com.tsa.puridiom.invoiceline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqLine;
import com.tsa.puridiom.historylog.tasks.InvoiceLineSetupHistoryValues;
import com.tsa.puridiom.historylog.tasks.RfqLineSetupHistoryValues;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceLineGetHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        HistoryLog history = null;
        try
        {
            Map incomingRequest = (Map)object;
            InvoiceLine newLine	= (InvoiceLine)incomingRequest.get("newHistoryInvoiceLine");
            InvoiceHeader header = (InvoiceHeader)incomingRequest.get("invoiceHeader");

            if(newLine == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("New Invoice Line was not found!");
            }

            /*
            String organizationId = (String) incomingRequest.get("organizationId");
            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            //String sAutoReleased = (String)incomingRequest.get("autoReleased");
             */
            String historyreason = (String)incomingRequest.get("historyreason");
            if(historyreason == null){	historyreason = "";	}
            //boolean autoReleased = Utility.ckNull(sAutoReleased).equals("Y");

            InvoiceLineSetupHistoryValues historyBuild = new InvoiceLineSetupHistoryValues();
            historyBuild.setOrganizationId((String)incomingRequest.get("organizationId"));
            historyBuild.setUserId((String)incomingRequest.get("userId"));
            historyBuild.setHistoryStatus(incomingRequest.get("historyStatus"));
            historyBuild.setReason(historyreason);
            //historyBuild.setAutoReleased(autoReleased);

            String forwardedTo = (String)incomingRequest.get("forwardedTo");
            if (forwardedTo == null)
            {
                forwardedTo = "";
            }
            else
            {
                forwardedTo = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), forwardedTo).getDisplayName();
            }
            historyBuild.setForwardedTo(forwardedTo);

            String rejectedBy = (String)incomingRequest.get("rejectedBy");
            if (rejectedBy == null)
            {
                rejectedBy = "";
            }
            else
            {
                rejectedBy = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
            }
            historyBuild.setRejectedBy(rejectedBy);

            historyBuild.setNextUser(forwardedTo);
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
