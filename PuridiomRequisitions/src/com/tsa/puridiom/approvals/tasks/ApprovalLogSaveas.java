package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class ApprovalLogSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain approvalLog */
			ApprovalLogPK comp_id = new ApprovalLogPK();
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			ApprovalLog	originalApprovalLog = (ApprovalLog) incomingRequest.get("approvalLog");
			ApprovalLog	approvalLog = new ApprovalLog();

			comp_id.setIcHeader(originalApprovalLog.getComp_id().getIcHeader());
			comp_id.setIcLine(originalApprovalLog.getComp_id().getIcLine());
			comp_id.setSequence(originalApprovalLog.getComp_id().getSequence());
			comp_id.setUserId(originalApprovalLog.getComp_id().getUserId());
			approvalLog.setAmount(originalApprovalLog.getAmount());
			approvalLog.setApproverAmount(originalApprovalLog.getApproverAmount());
			approvalLog.setApproved(originalApprovalLog.getApproved());
			approvalLog.setUdfValues(originalApprovalLog.getUdfValues());
			approvalLog.setAuthorized(originalApprovalLog.getAuthorized());
			approvalLog.setAlternateUserid(originalApprovalLog.getAlternateUserid());
			approvalLog.setDateAssigned(originalApprovalLog.getDateAssigned());
			approvalLog.setDateApproved(originalApprovalLog.getDateApproved());
			approvalLog.setApproverType(originalApprovalLog.getApproverType());
			approvalLog.setRuleType(originalApprovalLog.getRuleType());
			approvalLog.setPoolid(originalApprovalLog.getPoolid());
			approvalLog.setPooldesc(originalApprovalLog.getPooldesc());
			approvalLog.setRuleAction(originalApprovalLog.getRuleAction());
			approvalLog.setApproverSig(originalApprovalLog.getApproverSig());
			approvalLog.setCallForward(originalApprovalLog.getCallForward());
			approvalLog.setRuleSource(originalApprovalLog.getRuleSource());
			approvalLog.setApproverNotes(originalApprovalLog.getApproverNotes());
			approvalLog.setFyiApprover(originalApprovalLog.getFyiApprover());
			approvalLog.setRequiredApprover(originalApprovalLog.getRequiredApprover());
			approvalLog.setExcludeLess(originalApprovalLog.getExcludeLess());
			approvalLog.setBackupApprover(originalApprovalLog.getBackupApprover());
			approvalLog.setAdvisor(originalApprovalLog.getAdvisor());
			approvalLog.setRecommendation(originalApprovalLog.getRecommendation());
			approvalLog.setComp_id(comp_id);

			incomingRequest.put("approvalLog", approvalLog);

			ApprovalLogAdd addTask = new ApprovalLogAdd();
			approvalLog = (ApprovalLog) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = approvalLog;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
