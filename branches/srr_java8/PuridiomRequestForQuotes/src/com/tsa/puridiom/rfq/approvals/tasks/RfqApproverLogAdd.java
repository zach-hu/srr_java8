package com.tsa.puridiom.rfq.approvals.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RfqApproverLogAdd extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String ApprovalLog_userId = (String)incomingRequest.get("ApprovalLog_userId");
			String ApprovalLog_poolid = HiltonUtility.ckNull((String)incomingRequest.get("ApprovalLog_poolid"));
			UserProfile pUser = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), ApprovalLog_userId);
			if (pUser.getStatus().equals("02"))
	        {
				ApprovalLog appLog = new ApprovalLog();
				ApprovalLogPK pk = new ApprovalLogPK();
				String RfqHeader_icRfqHeader = (String)incomingRequest.get("RfqHeader_icRfqHeader");
				pk.setIcHeader(new BigDecimal(RfqHeader_icRfqHeader));
				appLog.setComp_id(pk);
				appLog.setRuleType("RFQ");
				appLog.setAmount(pUser.getWarrantAmount()) ;
				appLog.setApproverType("U");
				if (!HiltonUtility.isEmpty(ApprovalLog_poolid)) {
					appLog.setApproverType("P");
				}
				appLog.setPoolid(ApprovalLog_poolid) ;
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
                appLog.setRuleSource("RFQ");
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
