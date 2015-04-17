package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class SetupLastPoolIdAdminRequestApproval extends Task
{
	List toList = new ArrayList() ;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			String oid = (String)incomingRequest.get("organizationId") ;
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			BigDecimal icHeader = requisitionHeader.getIcReqHeader();
			List approverOriginal = new ArrayList();
			List routingListUpdate = new ArrayList();
			List resultReuseList = new ArrayList();
			String ruleError = HiltonUtility.ckNull((String) incomingRequest.get("ruleError"));

			List routingList = new ArrayList();
			if (incomingRequest.containsKey("routingList")) {
                // Used when generating approval routing list
                routingList = (List) incomingRequest.get("routingList");
            } else {
                // Used when retrieving approval routing list
                routingList = (List) incomingRequest.get("approvalLogList") ;
            }
			String	poolId = "ADMIN CHECK REV";    // Hack @
        	incomingRequest.put("AppPool_poolid",poolId) ;
        	incomingRequest.put("AppPooluser_poolid",poolId) ;
        	AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
            List poolUsersList = (List)task.executeTask(incomingRequest);

			if ((routingList != null && routingList.size() > 0))
            {
				for (int ix = 0; ix < routingList.size(); ix++)
				{
					/* Expects incoming request to contain approvalLog */
					ApprovalLogPK comp_id = new ApprovalLogPK();
					ApprovalLog	originalApprovalLog = (ApprovalLog) routingList.get(ix);
					String approverId = originalApprovalLog.getUserId();
					String ruleType = originalApprovalLog.getRuleType();
					String approverType = approverId + "-" + ruleType;
					approverOriginal.add(approverType);
					routingListUpdate.add(originalApprovalLog);
				}
            }
			if(poolUsersList != null && poolUsersList.size() > 0)
			{
				for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++)
				{
					AppPooluser poolUser = (AppPooluser) poolUsersList.get(poolIndex);
                    String	userId = poolUser.getComp_id().getUserId();
                    UserProfile pUser = UserManager.getInstance().getUser(oid, userId);

                    ApprovalLog appLog = new ApprovalLog();
   	                ApprovalLogPK pk = new ApprovalLogPK();
   	                String approverId = pUser.getUserId();
					String ruleType = "";
   	                if ((routingList != null && routingList.size() > 0))
		            {
						ApprovalLog	newApprovalLog = (ApprovalLog) routingList.get(0);
						String udfValues = newApprovalLog.getUdfValues();
						ruleType = newApprovalLog.getRuleType();
						appLog.setUdfValues(udfValues);
						appLog.setRuleType(ruleType);
						appLog.setApproved("N");
						String approverName = UserManager.getInstance().getUser(oid, approverId).getDisplayName();
						if(!HiltonUtility.isEmpty(approverName))
						{
							appLog.setApproverName(approverName);
						}
						pk.setIcHeader(newApprovalLog.getComp_id().getIcHeader());
	   	                pk.setIcLine(newApprovalLog.getComp_id().getIcLine());
	   	                appLog.setRuleType(newApprovalLog.getRuleType());
		            }
   	                else
   	                {
   	                	appLog.setUdfValues("Pool Id SRR-CHKADM");
   	                	appLog.setRuleType("EXT");
						appLog.setApproved("N");
						String approverName = UserManager.getInstance().getUser(oid, approverId).getDisplayName();
						if(!HiltonUtility.isEmpty(approverName))
						{
							appLog.setApproverName(approverName);
						}
						pk.setIcHeader(icHeader);
	   	                pk.setIcLine(new BigDecimal(0));
	   	                appLog.setRuleType("EXT");
   	                }
   	                String callForward = pUser.getCallForward() ;
   	                if (Utility.isEmpty(callForward)) {
   	                	callForward = userId ;
   	                }
   	                pk.setSequence(new BigDecimal(1));
					pk.setUserId(approverId);

					appLog.setAmount(new BigDecimal(10000000));
   	                appLog.setApproverAmount(new BigDecimal(10000000));
					appLog.setAdvisor("N");
					appLog.setApproved("N");
					appLog.setAuthorized("N");
					appLog.setAlternateUserid(approverId);
					appLog.setDateAssigned(Dates.getDate(Dates.today("")));
					appLog.setApproverType("U");
					appLog.setRuleAction("A");
					appLog.setPoolid(poolId);
					appLog.setCallForward(approverId);
					appLog.setApproverNotes("");
					appLog.setFyiApprover("N");
					appLog.setExcludeLess(new BigDecimal("0"));
					appLog.setBackupApprover("");
					appLog.setRecommendation("");
					appLog.setComp_id(pk);

					String approverType = approverId + "-" + ruleType;
					approverOriginal.add(approverType);
					routingListUpdate.add(appLog);
				}
			}

			for(int i = 0; i < approverOriginal.size(); i++)
			{
			    String approverComparator = (String) approverOriginal.get(i);
			    for(int ji = i+1; ji < approverOriginal.size(); ji++ )
			    {
			    	String approverComparatorAux = (String) approverOriginal.get(ji);
			        if (approverComparator.equalsIgnoreCase(approverComparatorAux))
			        {
			        	approverOriginal.remove(ji);
			        	routingListUpdate.remove(ji);
			        }
			    }
			}

			for(int ru = 0; ru < routingListUpdate.size(); ru++)
			{
				ApprovalLogPK comp_id = new ApprovalLogPK();
				ApprovalLog	originalApprovalLog = (ApprovalLog) routingListUpdate.get(ru);
				this.existApprover(originalApprovalLog);

			}

            if(HiltonUtility.isEmpty(ruleError))
        	{
            	incomingRequest.put("ruleError","") ;
        	}
            else
            {
                incomingRequest.put("ruleError",ruleError) ;
            }
            incomingRequest.put("ruleStatus","PASSED") ;
        	for(int i = 0; i < toList.size(); i++)
	        {
	            ApprovalLog userLog = (ApprovalLog) toList.get(i);
	            ApprovalLogPK pk = userLog.getComp_id();
	            pk.setSequence(new BigDecimal(i +1));
	            userLog.setComp_id(pk);
	        }
			result = toList ;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}

	public void existApprover(ApprovalLog appLog)
    {
        try
        {
        	ApprovalLog approvalLog = new ApprovalLog();
			ApprovalLogPK comp_id = new ApprovalLogPK();
			comp_id.setIcHeader(appLog.getComp_id().getIcHeader());
			comp_id.setIcLine(appLog.getComp_id().getIcLine());
			comp_id.setSequence(appLog.getComp_id().getSequence());
			comp_id.setUserId(appLog.getComp_id().getUserId());
			approvalLog.setApproverName(appLog.getApproverName()) ;
			approvalLog.setAmount(appLog.getAmount());
			approvalLog.setApproverAmount(appLog.getApproverAmount());
			approvalLog.setApproved(appLog.getApproved());
			approvalLog.setPoolid(appLog.getPoolid());
			approvalLog.setUdfValues(appLog.getUdfValues());
			approvalLog.setAuthorized(appLog.getAuthorized());
			approvalLog.setAlternateUserid(appLog.getAlternateUserid());
			approvalLog.setDateApproved(appLog.getDateApproved());
			approvalLog.setDateAssigned(Dates.getDate(Dates.today("")));
			approvalLog.setApproverType(appLog.getApproverType());
			approvalLog.setRuleType(appLog.getRuleType());
			approvalLog.setRuleAction(appLog.getRuleAction());
			approvalLog.setCallForward(appLog.getCallForward());
			approvalLog.setApproverNotes(appLog.getApproverNotes());
			approvalLog.setFyiApprover(appLog.getFyiApprover());
			approvalLog.setExcludeLess(appLog.getExcludeLess());
			approvalLog.setBackupApprover(appLog.getBackupApprover());
			approvalLog.setAdvisor(appLog.getAdvisor());
			approvalLog.setRecommendation(appLog.getRecommendation());
			approvalLog.setKeepApprover(appLog.getKeepApprover());
			approvalLog.setComp_id(comp_id);
			toList.add(approvalLog) ;
        }
        catch (Exception e)
        {
            Log.error(this, e.toString());
        }
    }
}