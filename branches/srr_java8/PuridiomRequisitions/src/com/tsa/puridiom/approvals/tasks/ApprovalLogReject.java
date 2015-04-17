/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.approvals.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApprovalLogReject extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        String newStatus = DocumentStatus.REQ_FORWARDED;

        try
        {
            Map incomingRequest = (Map)object;
            List routingList = (List)incomingRequest.get("routingList");

            String organizationId = (String)incomingRequest.get("organizationId") ;
            String userId = (String)incomingRequest.get("userId") ;
            String notes = (String)incomingRequest.get("ApprovalLog_approverNotes") ;
            String override = (String) incomingRequest.get("override");
            boolean overrideApproval = false;

            //check approval override settings
            if (UserManager.getInstance().getUser(organizationId, userId).isAnOverrider()) {
                if (override == null) {
                    override = "N";
                } else if (override.equals("Y")) {
                    overrideApproval = true;
                }
            }

            Map poolsToApprove = new HashMap();

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);

                if (approvalLog.getApproverType().equals("P")) {
                    // Pool Type
                    String  poolId = approvalLog.getPoolid();
                    incomingRequest.put("AppPooluser_poolid",poolId) ;

                    AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
                    List poolUsersList = (List)task.executeTask(incomingRequest);

                    for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++) {
                        AppPooluser poolUser = (AppPooluser) poolUsersList.get(poolIndex);
                        String  poolUserId = poolUser.getComp_id().getUserId();
                        if (poolUserId.equals(userId)) {
                            poolsToApprove.put(poolId, "Y");
                            break;
                        }
                    }
                }
            }

            for(int i = 0; i < routingList.size(); i++)
            {
                ApprovalLog approvalLog = (ApprovalLog)routingList.get(i);

                if (approvalLog.getApproved().equals("A") || approvalLog.getCallForward().equals(userId) || approvalLog.getBackupApprover().equals(userId)) {
                    if (userId.equals(approvalLog.getCallForward()) || userId.equals(approvalLog.getBackupApprover()) || overrideApproval || (approvalLog.getApproverType().equals("P") && poolsToApprove.containsKey(approvalLog.getPoolid()))) {
                	    // Reject current req
                		ApprovalLogUpdate appUpdate = new ApprovalLogUpdate();

                		approvalLog.setApproved("R") ;
                		approvalLog.setKeepApprovedFlag("");
                		approvalLog.setApproverNotes(notes) ;
                        approvalLog.setAlternateUserid(userId) ;

                        incomingRequest.put("approvalLog", approvalLog);
                        appUpdate.executeTask(incomingRequest);
                        this.setStatus(appUpdate.getStatus());
                        if(this.getStatus() != Status.SUCCEEDED)
                        {
                            throw new TsaException("An error ocurred writing a record for user: " + approvalLog.getComp_id().getUserId());
                        }
                        newStatus = DocumentStatus.REQ_REJECTED ;
                	}
                }
            }
            incomingRequest.put("rejectedBy",userId) ;
            incomingRequest.put("newStatus",newStatus) ;
            ret = routingList;
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return ret;
        //return null;
    }
}
