package com.tsa.puridiom.invoice.approvals.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class QueueAddInvoiceForwardCopyEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String forwardedTo = (String) incomingRequest.get("forwardTo");
            InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");
        	String organizationId = (String) incomingRequest.get("organizationId");

        	String invoiceNumber = ivh.getInvoiceNumber();

            incomingRequest.put("SendQueue_doctype", "IV");
            incomingRequest.put("SendQueue_docic", (String) incomingRequest.get("InvoiceHeader_icIvcHeader"));

            String owner = (String) incomingRequest.get("userId");

            StringBuffer subject = new StringBuffer("");
            subject.append(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName());
            subject.append(" has forwarded a copy of Invoice ");
            subject.append(invoiceNumber);
            if (organizationId.equalsIgnoreCase("ctb08p")) {
            	subject.append(", PO/Contract " + ivh.getPoNumber()) ;
            	subject.append(", Supplier/Contractor " + ivh.getVendorName()) ;
            	if (! HiltonUtility.isEmpty(ivh.getUdf1Code())) {
            		subject.append(", Log " + ivh.getUdf1Code()) ;
            	}
            }
            subject.append(".");

            incomingRequest.put("SendQueue_subject",subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), forwardedTo).getMailId() );

            incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAILNOACTION);
            String approverNotes = (String)incomingRequest.get("SendQueue_messagetext");
            incomingRequest.put("forwardedTo", forwardedTo);
            if(approverNotes != null)
            {
                incomingRequest.put("SendQueue_messagetext", approverNotes);
            }

            this.setStatus(Status.SUCCEEDED);

        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}