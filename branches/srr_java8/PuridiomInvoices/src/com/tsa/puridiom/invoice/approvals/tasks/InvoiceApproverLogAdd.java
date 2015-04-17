package com.tsa.puridiom.invoice.approvals.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.apppooluser.tasks.AppPooluserRetrieveByPool;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.AppPooluser;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceApproverLogAdd extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String userId = (String) incomingRequest.get("ApprovalLog_userId");
			String reqApprover = HiltonUtility.ckNull((String) incomingRequest.get("reqApprover"));
			UserProfile approver = UserManager.getInstance().getUser((String) incomingRequest.get("organizationId"), userId);

			String approverType = "U";
			String poolId = "";
			String poolDesc = "";

			if (reqApprover.equalsIgnoreCase("Y"))
			{
				approverType = "A";	//the approver of the requisition
			}

			if (userId.startsWith("@"))
			{
            	AppPoolRetrieveById retrievePoolTask = new AppPoolRetrieveById();
            	incomingRequest.put("AppPool_poolid", userId.substring(1));
            	incomingRequest.put("AppPooluser_poolid", userId.substring(1));
            	AppPool appPool = (AppPool) retrievePoolTask.executeTask(incomingRequest);
            	approverType = "P";
                poolId = appPool.getPoolid();
                poolDesc = appPool.getPooldesc();
            	/*
                AppPooluserRetrieveByPool task = new AppPooluserRetrieveByPool();
                List poolUsersList = (List)task.executeTask(incomingRequest);
                for(int poolIndex = 0; poolIndex < poolUsersList.size(); poolIndex++)
                {
                    AppPooluser poolUser = (AppPooluser)poolUsersList.get(poolIndex);
                    s_approver = poolUser.getComp_id().getUserId();
                    approverType = "P";
                    poolId = "PAYMENT";
                    poolDesc = appPool.getPooldesc();
                }
                */
			}

			if (approver.getStatus().equals("02") || userId.startsWith("@"))
	        {
				ApprovalLog appLog = new ApprovalLog();
				ApprovalLogPK pk = new ApprovalLogPK();
				String InvoiceHeader_icIvcHeader = (String) incomingRequest.get("InvoiceHeader_icIvcHeader");
				pk.setIcHeader(new BigDecimal(InvoiceHeader_icIvcHeader));
				pk.setIcLine(new BigDecimal(0));
				//appLog.setComp_id(pk);
				appLog.setRuleType("IVC");
				appLog.setAmount(approver.getApprovalAmount());
				appLog.setApproverType(approverType);
				appLog.setPoolid(poolId);
				appLog.setPooldesc(poolDesc);

	        	String callForward = approver.getCallForward();
	        	if(Utility.isEmpty(callForward))
	        	{
	        		callForward = userId;
	        	}
                appLog.setCallForward(callForward);

            	appLog.setApproverAmount(approver.getApprovalAmount());
            	appLog.setCallForward(callForward);
            	if (userId.startsWith("@"))
            	{
            		pk.setUserId(userId.substring(1));
            	}
            	else
            	{
            		pk.setUserId(userId);
            	}
    	        appLog.setComp_id(pk);
                appLog.setApproverName(approver.getDisplayName());
                appLog.setRuleSource("IVC");
    	        appLog.setApproved("N");
    	        appLog.setAuthorized("N");
    	        appLog.setRuleAction("A");
    	        appLog.setApproverSig("");
    	        appLog.setApproverNotes("");
    	        ret = appLog;
	        }
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Approval List could not be generated");
		}
		return ret;
	}


}
