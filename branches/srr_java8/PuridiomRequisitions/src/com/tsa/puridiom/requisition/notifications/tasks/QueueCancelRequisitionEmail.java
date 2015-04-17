package com.tsa.puridiom.requisition.notifications.tasks;

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

public class QueueCancelRequisitionEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
        	RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
        	String newStatus = requisitionHeader.getStatus() ;
        	String reqUser = requisitionHeader.getRequisitionerCode() ;
        	String buyerUser = requisitionHeader.getAssignedBuyer() ;

            String nextUser =	UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), reqUser).getMailId() +
            					"; " +
            					UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), buyerUser).getMailId() ;

            if(requisitionHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Order could not be forwarded for Approval!");
            }
            Log.debug(this, "Writting send_queue record for requisition: " + requisitionHeader.getRequisitionNumber() + ", status " + newStatus);

            if(newStatus.equals(DocumentStatus.CANCELLED) && !Utility.isEmpty(nextUser))
            {
            	String owner = (String) incomingRequest.get("userId") ;
                String userTimeZone = (String) incomingRequest.get("userTimeZone") ;
                StringBuffer subject = new StringBuffer("Requisition ");
                subject.append(requisitionHeader.getRequisitionNumber()) ;
                subject.append(" cancelled") ;
                StringBuffer message = new StringBuffer("Requisition ");
                message.append(requisitionHeader.getRequisitionNumber()) ;
                message.append(" was cancelled by " + UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getDisplayName() );
                message.append(" on " + Dates.today("", userTimeZone) + " " + userTimeZone) ;

            	incomingRequest.put("SendQueue_doctype", "REQ");
                incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));
                incomingRequest.put("SendQueue_subject", subject.toString() );
                incomingRequest.put("SendQueue_messagetext", message.toString() );
                incomingRequest.put("SendQueue_owner", owner );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), owner).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", nextUser );
                incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);

                PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
                PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
                process.executeProcess(incomingRequest);
                this.status = process.getStatus() ;
            }
            else
            {
                this.setStatus(Status.SUCCEEDED);
                Log.debug(this, "No record will be written for this Req: " + requisitionHeader.getRequisitionNumber() );
            }
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Requisition Approval Email couldn't be processed.", e);
        }
        return result;
    }
}