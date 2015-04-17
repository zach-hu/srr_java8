package com.tsa.puridiom.approvals;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddAppByOverriderRequisitionerEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String	organizationId = (String)incomingRequest.get("organizationId");
            String  userId = (String) incomingRequest.get("userId");
            String  overrideAction = (String) incomingRequest.get("overrideAction");
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");

            if(rqh == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " RequisitionHeader was not found!");
            }
            if(Utility.isEmpty(overrideAction))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Approval override action was not found!");
            }
            if(Utility.isEmpty(userId))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " Current user id was not found!");
            }
            String	requisitionNumber = rqh.getRequisitionNumber();
            String	requisitionerCode = rqh.getRequisitionerCode();
            if(requisitionNumber == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " RequisitionNumber was not found!");
            }
            if (Utility.isEmpty(requisitionerCode))
            {
                requisitionerCode = rqh.getOwner();
                if (Utility.isEmpty(requisitionerCode))
                {
	                this.setStatus(Status.FAILED);
	                throw new TsaException(this.getName() + " Requisitioner / Owner were not found.  Email record cannot be written to notify the requisitioner that the requisition has been approved by the backup approver!");
                }
            }

            ApprovalLog	approvalLog = (ApprovalLog) incomingRequest.get("approver");
            if (approvalLog == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " ApprovalLog record was not found.  Email record cannot be written to notify the initial approver that the requisition has been approved by the backup approver!");
            }

            String	currentApprover = approvalLog.getCallForward();

            if (Utility.isEmpty(currentApprover))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " CurrentApprover was not found.  Email record cannot be written to notify the requisitioner that the requisition has been approved by the backup approver!");
            }

            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));

            StringBuffer subject = new StringBuffer("");
            subject.append(UserManager.getInstance().getUser(organizationId, userId).getDisplayName());
            subject.append(" has " + overrideAction + " Requisition ");
            subject.append(requisitionNumber);
            subject.append(" on behalf of ");
            subject.append(UserManager.getInstance().getUser(organizationId, currentApprover).getDisplayName());
            subject.append(".");

            incomingRequest.put("SendQueue_subject",subject.toString() );
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, userId).getMailId() );
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, requisitionerCode).getMailId() );
            incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
            incomingRequest.put("SendQueue_messagetext", subject.toString());
            incomingRequest.put("isRequisitionForwardApprovalProcess", "Y");

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");
            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
            this.status = Status.FAILED;
            throw e;
        }
        return result;
    }
}