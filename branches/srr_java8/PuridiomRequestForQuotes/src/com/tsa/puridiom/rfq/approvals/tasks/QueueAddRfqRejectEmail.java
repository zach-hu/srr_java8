package com.tsa.puridiom.rfq.approvals.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddRfqRejectEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String newStatus = (String)incomingRequest.get("newStatus") ;
            String rejectedBy = (String)incomingRequest.get("rejectedBy") ;
            String rfqNumber =  (String) incomingRequest.get("RfqHeader_rfqNumber") ;
            String userTimeZone = (String)incomingRequest.get("userTimeZone") ;
            RfqHeader rfqh = (RfqHeader)incomingRequest.get("rfqHeader");
            if(rfqNumber == null)
            {
                rfqNumber = rfqh.getDisplayRfqNumber().toString();
                if(rfqNumber == null)
                {
                    this.setStatus(Status.FAILED);
                    throw new TsaException(this.getName() + " Solicitation was not found!");
                }
            }

            if(newStatus.equals(DocumentStatus.RFQ_REJECTED) && !Utility.isEmpty(rejectedBy))
            {
                incomingRequest.put("SendQueue_doctype", "RFQ");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RfqHeader_icRfqHeader"));


                String owner = (String) incomingRequest.get("userId") ;

                StringBuffer subject = new StringBuffer("");
                subject.append(rfqNumber);
                subject.append(" was Rejected By ");
                String tempName = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), rejectedBy).getDisplayName();
                subject.append(tempName);

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), rfqh.getBuyer()).getMailId() );
                incomingRequest.put("SendQueue_action", "EN");
                String ApprovalLog_approverNotes = (String)incomingRequest.get("ApprovalLog_approverNotes");
                StringBuffer rejectMessage = new StringBuffer();
                rejectMessage.append(subject + "\n");
                rejectMessage.append("on " + Dates.today("", userTimeZone) + " " + userTimeZone);
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
                Log.debug(this, "No record will be written for this Req: " + rfqNumber );
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