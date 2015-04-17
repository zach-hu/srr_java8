package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class SetupReuseApproverApproval extends Task
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
			String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader");
			BigDecimal icHeader = new BigDecimal(icReqHeader) ;
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
			resultReuseList = (List) incomingRequest.get("reuseRoutingList") ;

			if ((resultReuseList != null && resultReuseList.size() > 0))
            {
                for (int ir = 0; ir < resultReuseList.size(); ir++)
                {
                	ApprovalLogPK comp_id = new ApprovalLogPK();
					ApprovalLog	originalApprovalLog = (ApprovalLog) resultReuseList.get(ir);
					String approverId = originalApprovalLog.getUserId();
					String ruleType = originalApprovalLog.getRuleType();

					//Prior approval should keep original values related to rules so this is not necessary
					/*if ((routingList != null && routingList.size() > 0))
		            {
						ApprovalLog	newApprovalLog = (ApprovalLog) routingList.get(0);
						String udfValues = newApprovalLog.getUdfValues();
						ruleType = newApprovalLog.getRuleType();
						originalApprovalLog.setUdfValues(udfValues);
						originalApprovalLog.setRuleType(ruleType);
		            }*/
													
					if(HiltonUtility.isEmpty(originalApprovalLog.getKeepApprovedFlag()))
					{
						originalApprovalLog.setKeepApprovedFlag(originalApprovalLog.getApproved());
					}
					originalApprovalLog.setApproved("N");
					String approverName = UserManager.getInstance().getUser(oid, approverId).getDisplayName();
					if(!HiltonUtility.isEmpty(approverName))
					{
						originalApprovalLog.setApproverName(approverName);
					}
					String approverType = approverId + "-" + ruleType;
					approverOriginal.add(approverType);
					routingListUpdate.add(originalApprovalLog);

                }
            }
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
				this.existApprover(originalApprovalLog,incomingRequest);

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

	public void existApprover(ApprovalLog appLog, Map incomingRequest)
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
			approvalLog.setKeepApprovedFlag(appLog.getKeepApprovedFlag());
			approvalLog.setUdfValues(appLog.getUdfValues());
			approvalLog.setAuthorized(appLog.getAuthorized());
			approvalLog.setAlternateUserid(appLog.getAlternateUserid());
			approvalLog.setDateApproved(appLog.getDateApproved());
			if(appLog.getDateAssigned() != null){
				approvalLog.setDateAssigned(appLog.getDateAssigned());
			}else{
				approvalLog.setDateAssigned(Dates.getCurrentDate((String) incomingRequest.get("userTimeZone")));
			}
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