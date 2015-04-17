package com.tsa.puridiom.approvals;

import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class QueueAddAppByBackupApproverEmail extends Task
{
    public Object executeTask(Object object) throws Exception
    {
        Map incomingRequest = (Map)object;
        Object result = null;

        try
        {
            String	organizationId = (String)incomingRequest.get("organizationId");
            String backupApproverAction = (String)incomingRequest.get("backupApproverAction") ;
            RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            if(rqh == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " RequisitionHeader was not found!");
            }
            String	requisitionNumber = rqh.getRequisitionNumber();
            if(requisitionNumber == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " RequisitionNumber was not found!");
            }

            ApprovalLog	approvalLog = (ApprovalLog) incomingRequest.get("approver");
            if (approvalLog == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " ApprovalLog record was not found.  Email record cannot be written to notify the initial approver that the requisition has been approved by the backup approver!");
            }

            String	backupApprover = approvalLog.getBackupApprover();
            String	currentApprover = approvalLog.getCallForward();

            if (Utility.isEmpty(backupApprover))
            {
                //this.setStatus(Status.FAILED);
                //throw new TsaException(this.getName() + " BackupApprover was not found.  Email record cannot be written to notify the requisitioner that the requisition has been approved by the backup approver!");
            }
            if (Utility.isEmpty(currentApprover))
            {
                this.setStatus(Status.FAILED);
                throw new TsaException(this.getName() + " CurrentApprover was not found.  Email record cannot be written to notify the requisitioner that the requisition has been approved by the backup approver!");
            }

            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));

            if (!Utility.isEmpty(requisitionNumber))
            {
                String owner = (String) incomingRequest.get("userId") ;

                StringBuffer subject = new StringBuffer("");
                subject.append(UserManager.getInstance().getUser(organizationId, backupApprover).getDisplayName());
                subject.append(" has " + backupApproverAction + " Requisition ");
                subject.append(requisitionNumber);
                subject.append(" on your behalf.  No action is required from you at this time.");

                incomingRequest.put("SendQueue_subject",subject.toString() );
                incomingRequest.put("SendQueue_sendfromtype", "E");
                incomingRequest.put("SendQueue_sendfrom", UserManager.getInstance().getUser(organizationId, owner).getMailId() );
                incomingRequest.put("SendQueue_sendtotype", "E");
                incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, currentApprover).getMailId() );
                incomingRequest.put("SendQueue_action", EmailActionCodes.EMAIL);
                incomingRequest.put("SendQueue_messagetext", subject.toString());
                incomingRequest.put("isRequisitionForwardApprovalProcess", "Y");

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