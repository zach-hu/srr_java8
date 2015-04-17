package com.tsa.puridiom.approvals;

import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class QueueAddReqForwardCopyEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String forwardedTo = (String)incomingRequest.get("forwardTo") ;
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            String requisitionNumber = rqh.getRequisitionNumber();

            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));

            String owner = (String) incomingRequest.get("userId") ;

            StringBuffer subject = new StringBuffer("");
            subject.append(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName());
            subject.append(" has forwarded a copy of Requisition ");
            subject.append(requisitionNumber);
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