package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class ApprovalLogSetPoolUsers extends Task {

    public Object executeTask(Object object) throws Exception {
        Map incomingRequest = (Map)object;
        Object result = null;

        try {
            List routingList = (List) incomingRequest.get("routingList");
            List newRoutingList = new ArrayList();
            String	organizationId = (String) incomingRequest.get("organizationId");

            if (routingList == null) {
                return null;
            }
            for (int i=0; i < routingList.size(); i++) {
                ApprovalLog currentApprovalLog = (ApprovalLog) routingList.get(i);
	        	String approverType = currentApprovalLog.getApproverType() ;

	            if(approverType.equals("P")) {
	                String	poolId = currentApprovalLog.getPoolid();
	            	incomingRequest.put("AppPool_poolid",poolId) ;
	            	incomingRequest.put("AppPooluser_poolid",poolId) ;
	            	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
	            	AppPool appPool = (AppPool) pool.executeTask(incomingRequest);

//	            	String pFlag = "P" ;
//	            	if (appPool != null) {
//	            		pFlag = appPool.getPoolflag1() ;
//	            	}
//	            	if (pFlag.equalsIgnoreCase("Y")) {
	            	    //Add pool users to the routing list in place of this pool record
						AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
		                List poolUsersList = (List)task.executeTask(incomingRequest);
		                BigDecimal sequence = currentApprovalLog.getComp_id().getSequence();

		                for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++) {
		                    AppPooluser poolUser = (AppPooluser) poolUsersList.get(poolIndex);
		                    String	userId = poolUser.getComp_id().getUserId();
		                    UserProfile pUser = UserManager.getInstance().getUser(organizationId, userId);

		                    if (pUser.getStatus().equals("02")) {
//		                        sequence = sequence.add(new BigDecimal("0.01"));

			                    // Add approver to approval_log
			                    ApprovalLog appLog = new ApprovalLog();
			   	                ApprovalLogPK pk = new ApprovalLogPK();
			   	                pk.setIcHeader(currentApprovalLog.getComp_id().getIcHeader());
			   	                pk.setIcLine(currentApprovalLog.getComp_id().getIcLine());
			   	                pk.setSequence(sequence);
			   	                pk.setUserId(userId);
			   	                appLog.setComp_id(pk);

		        	        	String callForward = pUser.getCallForward() ;
		                        if (Utility.isEmpty(callForward)) {
		                        	callForward = userId ;
		                        }

		                        appLog.setAdvisor(currentApprovalLog.getAdvisor());
		                        appLog.setAmount(currentApprovalLog.getAmount());
		                        appLog.setApproved("N");
		                        appLog.setApproverAmount(currentApprovalLog.getApproverAmount()) ;
		                    	appLog.setApproverName(pUser.getDisplayName());
		                    	appLog.setApproverNotes("");
		            	        appLog.setApproverSig("");
		                    	appLog.setApproverType("U");
		            	        appLog.setAuthorized("N");
		                    	appLog.setCallForward(callForward);
		                        appLog.setExcludeLess(currentApprovalLog.getExcludeLess());
		                        appLog.setFyiApprover(currentApprovalLog.getFyiApprover());
		                        appLog.setPoolid(currentApprovalLog.getPoolid());
		                        appLog.setPooldesc(currentApprovalLog.getPooldesc());
		                        appLog.setRequiredApprover(currentApprovalLog.getRequiredApprover());
		                        appLog.setRuleAction(currentApprovalLog.getRuleAction());
		                        appLog.setRuleNotes(currentApprovalLog.getRuleNotes());
		            	        appLog.setRuleSource(currentApprovalLog.getRuleSource());
		            	        appLog.setRuleType(currentApprovalLog.getRuleType());
		            	        appLog.setUdfValues(currentApprovalLog.getUdfValues());

		            	        newRoutingList.add(appLog) ;
		        	        }
		                }
		                // Remove record containing current pool id, since we just added all the pool users
		                //routingList.remove(i);
//	            	}
	            } else {
                 newRoutingList.add(currentApprovalLog);
                }

        	}
            result = routingList;
	        this.status = Status.SUCCEEDED;
        }
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result ;
	}
}