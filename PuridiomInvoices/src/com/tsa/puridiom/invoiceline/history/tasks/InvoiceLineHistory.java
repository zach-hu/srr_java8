/*
 * Created on August 27, 2004
 *
 * project: HiltonRequestForQuotes
 * package com.tsa.puridiom.rfqline.tasks.RfqLineHistory.java
 *
 */
package com.tsa.puridiom.invoiceline.history.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;


import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceLineHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
    	try
        {
            Map incomingRequest = (Map) object;
            InvoiceLine newLine = (InvoiceLine) incomingRequest.get("newHistoryInvoiceLine");
            if(!Utility.isEmpty(newLine.getInvoiceNumber()) && !newLine.getInvoiceNumber().equals("N/A"))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("invoiceline-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                String oid = (String)incomingRequest.get("organizationId");
                newIncomingRequest.put("organizationId", oid);
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
						       newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("invoiceHeader", incomingRequest.get("invoiceHeader"));
                newIncomingRequest.put("newHistoryInvoiceLine", newLine);
                newIncomingRequest.put("autoReleased", incomingRequest.get("autoReleased"));
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                newIncomingRequest.put("historyreason", incomingRequest.get("historyreason"));
                newIncomingRequest.put("forwardedTo", incomingRequest.get("forwardedTo"));
                newIncomingRequest.put("rejectedBy", incomingRequest.get("rejectedBy"));

                String forwadedTo = (String)incomingRequest.get("forwardedTo");
                forwadedTo = UserManager.getInstance().getUser(oid, forwadedTo).getDisplayName();
                newIncomingRequest.put("forwardedTo", forwadedTo);

                process.executeProcess(newIncomingRequest);
                this.setStatus(process.getStatus());
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
           throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}