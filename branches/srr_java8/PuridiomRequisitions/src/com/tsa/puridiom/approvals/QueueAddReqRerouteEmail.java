package com.tsa.puridiom.approvals;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddReqRerouteEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String newStatus = (String)incomingRequest.get("newStatus") ;
            String forwardedTo = (String)incomingRequest.get("routTo") ;
            String requisitionNumber =  (String) incomingRequest.get("RequisitionHeader_requisitionNumber") ;
            String timeZone = (String) incomingRequest.get("userTimeZone");
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(requisitionNumber == null)
            {
                requisitionNumber = rqh.getRequisitionNumber();
                if(requisitionNumber == null)
                {
                    this.setStatus(Status.FAILED);
                    throw new TsaException(this.getName() + " Requisition was not found!");
                }
            }

            if(newStatus.equals(DocumentStatus.REQ_APPROVING) && !Utility.isEmpty(forwardedTo))
            {
                incomingRequest.put("SendQueue_doctype", "REQ");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));


                String owner = (String) incomingRequest.get("userId") ;

                StringBuffer subject = new StringBuffer("");
                subject.append(UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName());
                subject.append(" has forwarded Requisition ");
                subject.append(requisitionNumber);
                subject.append(" for your approval.");

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), forwardedTo).getMailId() );

                incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAIL);
                String ApprovalLog_approverNotes = (String)incomingRequest.get("ApprovalLog_approverNotes");
                StringBuffer rejectMessage = new StringBuffer();
                rejectMessage.append(subject + "\n");
                rejectMessage.append("on " + Dates.today("", timeZone) + " " + timeZone);
                rejectMessage.append("\n");


                if(ApprovalLog_approverNotes != null)
                {
                    rejectMessage.append("Approver Notes:\n");
                    rejectMessage.append(ApprovalLog_approverNotes);
                    incomingRequest.put("SendQueue_messagetext", rejectMessage.toString());
                }
                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Req: " + requisitionNumber );
            }
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}