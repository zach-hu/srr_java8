package com.tsa.puridiom.po.approvals.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class PoApproverLogAdd extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String ApprovalLog_userId = (String)incomingRequest.get("ApprovalLog_userId");
			UserProfile pUser = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), ApprovalLog_userId);
			if (pUser.getStatus().equals("02"))
	        {
				ApprovalLog appLog = new ApprovalLog();
				ApprovalLogPK pk = new ApprovalLogPK();
				String PoHeader_icPoHeader = (String)incomingRequest.get("PoHeader_icPoHeader");
				pk.setIcHeader(new BigDecimal(PoHeader_icPoHeader));
				appLog.setComp_id(pk);
				appLog.setRuleType("PO");
				appLog.setAmount(pUser.getWarrantAmount()) ;
				appLog.setApproverType("u");
				appLog.setPoolid("") ;
	        	String callForward = pUser.getCallForward() ;
	        	if(Utility.isEmpty(callForward))
	        	{
	        		callForward = ApprovalLog_userId;
	        	}
                appLog.setCallForward(callForward) ;

            	appLog.setApproverAmount(pUser.getWarrantAmount()) ;
            	appLog.setCallForward(callForward) ;
    	        pk.setUserId(ApprovalLog_userId);
    	        appLog.setComp_id(pk);
                appLog.setApproverName(pUser.getDisplayName());
                appLog.setRuleSource("PO");
    	        appLog.setApproved("N") ;
    	        appLog.setAuthorized("N") ;
    	        appLog.setRuleAction("A") ;
    	        appLog.setApproverSig("") ;
    	        appLog.setApproverNotes("") ;
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
