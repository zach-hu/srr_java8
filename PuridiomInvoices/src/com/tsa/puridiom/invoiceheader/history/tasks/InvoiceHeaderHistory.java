/*
 * Created on October 16, 2006
 *
 * project: HiltonInvoices
 * package com.tsa.puridiom.invoiceheader.tasks.InvoiceHeaderHistory.java
 *
 */
package com.tsa.puridiom.invoiceheader.history.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceHeaderHistory extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        try
        {
            Map incomingRequest = (Map) object;
            InvoiceHeader newHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
            if (newHeader != null && !Utility.isEmpty(newHeader.getInvoiceNumber()))
            {
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
                PuridiomProcess process = processLoader.loadProcess("invoice-history.xml");

                Map newIncomingRequest = new HashMap();
                newIncomingRequest.put("dbsession", incomingRequest.get("dbsession"));
                newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
                newIncomingRequest.put("userId", incomingRequest.get("userId"));
						       newIncomingRequest.put("userTimeZone", incomingRequest.get("userTimeZone"));
                newIncomingRequest.put("InvoiceHeader_oldStatus", incomingRequest.get("InvoiceHeader_status"));
                newIncomingRequest.put("newHistoryInvoiceHeader", newHeader);
                newIncomingRequest.put("historyStatus", incomingRequest.get("historyStatus"));
                
                String deferTo = (String)incomingRequest.get("deferTo") ;
                
                if(!HiltonUtility.isEmpty(deferTo)){
                	newIncomingRequest.put("deferTo", deferTo);
                }
                
                if (incomingRequest.containsKey("ApprovalLog_approverNotes")) {
                    newIncomingRequest.put("ApprovalLog_approverNotes", incomingRequest.get("ApprovalLog_approverNotes"));
                }

                String forwardTo = (String)incomingRequest.get("forwardedTo");
                if (forwardTo == null)
                {
                    forwardTo = "";
                }
                newIncomingRequest.put("forwardedTo", forwardTo);

                String rejectedBy = (String)incomingRequest.get("rejectedBy");
                if (rejectedBy == null)
                {
                    rejectedBy = "";
                }
                newIncomingRequest.put("rejectedBy", rejectedBy);

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