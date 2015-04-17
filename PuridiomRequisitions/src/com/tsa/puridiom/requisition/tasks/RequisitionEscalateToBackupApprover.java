/*
 * Created on Jan 10, 2006
 */
package com.tsa.puridiom.requisition.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;
/**
 * @author Kelli
 */
public class RequisitionEscalateToBackupApprover extends Task {
    public Object executeTask(Object object) throws Exception {
        Object ret = null;
        try {
	        Map incomingRequest = (Map) object;

	        String	oid = (String) incomingRequest.get("organizationId");
	        String	uid = (String) incomingRequest.get("userId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

	        ApprovalLog approvalLog = (ApprovalLog) incomingRequest.get("approvalLog");
	        if (approvalLog != null) {
	            String currentAppUid = approvalLog.getBackupApprover();

	            if (Utility.isEmpty(currentAppUid)) {
	                currentAppUid = approvalLog.getCallForward();
	            }
	            if (Utility.isEmpty(currentAppUid)) {
	                currentAppUid = approvalLog.getComp_id().getUserId();
	            }

                UserProfile userProfile = UserManager.getInstance().getUser(oid, currentAppUid);
                String	requestedBy = UserManager.getInstance().getUser(oid, uid).getDisplayName();
                String	fromSystemAlerts = (String) incomingRequest.get("fromSystemAlerts");

                if (fromSystemAlerts != null && fromSystemAlerts.equals("Y")) {
                    requestedBy = "System Automation";
                }

	            if (userProfile != null && userProfile.isAnApprover() && userProfile.isRegistered()) {
	                String	backupApproverId = userProfile.getBackupApprover();
	                UserProfile backupApprover = UserManager.getInstance().getUser(oid, backupApproverId);

	                if (HiltonUtility.isEmpty(backupApproverId))
	                {
	                	incomingRequest.put("backupApproverExists", "N");
                        incomingRequest.put("validBackupApprover", "N");
                        incomingRequest.put("currentApproverUser", userProfile);
	                }
	                else
	                {
    	                BigDecimal minimumBackupApprovalAmount = new BigDecimal( PropertiesManager.getInstance(oid).getProperty("APPROVALS", "MINIMUMBACKUPAPPROVALAMOUNT", "0") );

    	                if ( backupApprover.getApprovalAmount().compareTo(minimumBackupApprovalAmount) >= 0 )
    	                {
    	                	incomingRequest.put("minimumBackupApprovalAmount", "Y");

                            String  approverNotes = approvalLog.getApproverNotes();
        	                if (!Utility.isEmpty(approverNotes))
        	                {
        	                    approverNotes = approverNotes + "/n";
        	                }
        	                approverNotes = approverNotes + "Approval escalated from " + userProfile.getDisplayName() + " to " + backupApprover.getDisplayName() + " on " + Dates.today("", userTimeZone) + " " + userTimeZone + " by " + requestedBy;
        	                approvalLog.setBackupApprover(backupApproverId);
        	                approvalLog.setApproverNotes(approverNotes);

                            incomingRequest.put("validBackupApprover", "Y");
        	                incomingRequest.put("backupApprover", backupApproverId);
        	                incomingRequest.put("currentApproverUser", userProfile);
        	                incomingRequest.put("backupApproverUser", backupApprover);

    	                }
    	                else
    	                {
    	                	incomingRequest.put("minimumBackupApprovalAmount", "N");
    	                }
                    }
	            } else {
	                incomingRequest.put("validBackupApprover", "N");
	            }
	        }

	        ret = approvalLog;

	        this.setStatus(Status.SUCCEEDED);
        } catch (Exception e) {
            this.status = Status.FAILED;
            Log.error(this, e.getMessage());
            throw e;
        }
        return ret ;
    }
}
