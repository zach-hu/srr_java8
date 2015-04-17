package com.tsa.puridiom.approvals;

import java.util.Map;

import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
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

public class QueueAddReqFYIApprovalEmail extends Task {
    public Object executeTask(Object object) throws Exception {
        Map incomingRequest = (Map)object;
        Object result = null;

        try {
            String requisitionNumber =  (String) incomingRequest.get("RequisitionHeader_requisitionNumber") ;
            if (requisitionNumber == null) {
                RequisitionHeader rqh = (RequisitionHeader)incomingRequest.get("requisitionHeader");
                requisitionNumber = rqh.getRequisitionNumber();
                if (requisitionNumber == null) {
                    this.setStatus(Status.FAILED);
                    throw new TsaException(this.getName() + " Requisition was not found!");
                }
            }
            Log.debug(this, "Writting send_queue record (FYI Notification) for requisition: " + requisitionNumber);

            String	organizationId = (String)incomingRequest.get("organizationId");
            String owner = (String) incomingRequest.get("userId") ;
            String	sendfrom = (String) incomingRequest.get("userId") ;
            ApprovalLog approvalLog = (ApprovalLog) incomingRequest.get("approvalLog");
            String extraSubjectFYI = HiltonUtility.ckNull((String)incomingRequest.get("extraSubjectFYI"));

            if(Utility.isEmpty(owner)) {
                sendfrom = (String) incomingRequest.get("SendQueue_sendfrom") ;
            } else {
                sendfrom = UserManager.getInstance().getUser(organizationId, owner).getMailId();
            }

            StringBuffer subject = new StringBuffer("");
            subject.append("FYI: Requisition ");
            subject.append(requisitionNumber) ;
            subject.append(" submitted for approval") ;
            subject.append(extraSubjectFYI) ;

            incomingRequest.put("SendQueue_doctype", "REQ");
            incomingRequest.put("SendQueue_docic", (String)incomingRequest.get("RequisitionHeader_icReqHeader"));
            incomingRequest.put("SendQueue_subject",subject.toString());
            incomingRequest.put("SendQueue_sendfromtype", "E");
            incomingRequest.put("SendQueue_sendfrom", sendfrom);
            incomingRequest.put("SendQueue_sendtotype", "E");
            incomingRequest.put("SendQueue_sendto", UserManager.getInstance().getUser(organizationId, approvalLog.getCallForward()).getMailId());
            incomingRequest.put("SendQueue_action", EmailActionCodes.HTMLEMAILNOACTION);
            incomingRequest.put("isRequisitionForwardApprovalProcess", "Y");

            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
            PuridiomProcess process = processLoader.loadProcess("sendqueue-add.xml");

            process.executeProcess(incomingRequest);
            this.status = process.getStatus() ;
        }
        catch (Exception e)
        {
           this.setStatus(Status.FAILED);
            throw new TsaException("Error processing HTML FYI approval notification.");
        }
        return result;
    }
}