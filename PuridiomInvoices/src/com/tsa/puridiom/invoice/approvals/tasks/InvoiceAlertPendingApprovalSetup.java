/*
 * Created on May 26, 2006
 */
package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.*;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility ;
import com.tsagate.foundation.processengine.Status;

public class InvoiceAlertPendingApprovalSetup extends Task
{
    public Object executeTask(Object object) throws Exception
    {

        Map incomingRequest = (Map) object;

        try
        {
        	String icHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
            if (Utility.isEmpty(icHeader))
            {
                icHeader = (String) incomingRequest.get("ApprovalLog_icHeader");
            }
            if (Utility.isEmpty(icHeader))
            {
                this.setStatus(Status.FAILED);
                throw new Exception("InvoiceHeader_icIvcHeader cannot be empty.  Invoice could not be retrieved.");
            }
            else
            {
                incomingRequest.put("ApprovalLog_icHeader", icHeader);
                incomingRequest.put("InvoiceHeader_icIvcHeader", icHeader);
                incomingRequest.put("InvoiceLine_icIvcHeader", icHeader);
                incomingRequest.put("ApprovalLog_icLine", "0");
                InvoiceHeader invoiceHeader = (InvoiceHeader)incomingRequest.get("invoiceHeader");
                incomingRequest.put("headerStatus", invoiceHeader.getStatus());

            }
            this.setStatus(Status.SUCCEEDED);
		}
        catch (Exception e)
        {
			this.setStatus(Status.FAILED);
			throw new TsaException("Invoice Approvals could not be completed!", e);
		}

        return null;
    }
}
