package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsa.puridiom.entity.ApprovalLogPK;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RequisitionRejectByBuyerAddRouting extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			String oid = (String)incomingRequest.get("organizationId");

			List routingList = (List)incomingRequest.get("routingList");
			String user = (String)incomingRequest.get("userId");
			UserProfile appRuleUser = UserManager.getInstance().getUser(oid, user);
			ApprovalLog appLog = new ApprovalLog();
			ApprovalLogPK pk = new ApprovalLogPK();
            pk.setIcHeader(header.getIcReqHeader());
            pk.setIcLine(new BigDecimal(0));
            pk.setUserId(user);
            pk.setSequence(new BigDecimal(routingList.size() + 1)) ;
            appLog.setComp_id(pk);
            BigDecimal approverAmount = appRuleUser.getApprovalAmount() ;
            appLog.setRuleSource(" ");
            appLog.setAmount(header.getTotal()) ;
            appLog.setApproverAmount(approverAmount) ;
            appLog.setApproverType("U");
            appLog.setPoolid(" ") ;
            appLog.setApproverName(appRuleUser.getDisplayName());
            appLog.setApproved("R") ;
            appLog.setAuthorized("N") ;
            appLog.setRuleAction("y") ;
            appLog.setApproverSig("") ;
            appLog.setRuleType("BUY") ;
            appLog.setRequiredApprover("Y");
            appLog.setUdfValues("ORIGINAL-BUYER");
            appLog.setCallForward(user);
            appLog.setExcludeLess(appRuleUser.getExcludeLess()) ;

            String temp = (String)incomingRequest.get("ApprovalLog_approverNotes");
            appLog.setApproverNotes(temp) ;

            ret = appLog;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Requisition could not be Rejected!", e);
		}
		return ret;
	}
}
