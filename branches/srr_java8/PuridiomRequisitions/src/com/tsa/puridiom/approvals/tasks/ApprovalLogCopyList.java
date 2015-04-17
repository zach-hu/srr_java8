package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import com.tsagate.foundation.utility.Dates;

import java.util.*;
import java.math.BigDecimal ;

public class ApprovalLogCopyList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List fromList = (List) incomingRequest.get("fromRoutingList") ;
			List toList = new ArrayList() ;
			String icReqHeader = (String)incomingRequest.get("RequisitionHeader_icReqHeader") ;
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
			BigDecimal icHeader = new BigDecimal(icReqHeader) ;
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			for (int ix = 0; ix < fromList.size(); ix++) {

				/* Expects incoming request to contain approvalLog */
				ApprovalLogPK comp_id = new ApprovalLogPK();
				ApprovalLog	originalApprovalLog = (ApprovalLog) fromList.get(ix);
				ApprovalLog	approvalLog = new ApprovalLog();

				comp_id.setIcHeader(icHeader);
				comp_id.setIcLine(originalApprovalLog.getComp_id().getIcLine());
				comp_id.setSequence(originalApprovalLog.getComp_id().getSequence());
				comp_id.setUserId(originalApprovalLog.getComp_id().getUserId());
				approvalLog.setApproverName(originalApprovalLog.getApproverName()) ;
				approvalLog.setAmount(originalApprovalLog.getAmount());
				approvalLog.setApproverAmount(originalApprovalLog.getApproverAmount());
				approvalLog.setApproved("N");
				approvalLog.setUdfValues(originalApprovalLog.getUdfValues());
				approvalLog.setAuthorized(originalApprovalLog.getAuthorized());
				approvalLog.setAlternateUserid(originalApprovalLog.getAlternateUserid());
				approvalLog.setDateAssigned(Dates.getDate(Dates.today("", userTimeZone)));
//				approvalLog.setDateApproved(originalApprovalLog.getDateApproved());
				approvalLog.setApproverType(originalApprovalLog.getApproverType());
				approvalLog.setRuleType(originalApprovalLog.getRuleType());
				approvalLog.setPoolid(originalApprovalLog.getPoolid());
				approvalLog.setPooldesc(originalApprovalLog.getPooldesc());
				approvalLog.setRuleAction(originalApprovalLog.getRuleAction());
				approvalLog.setApproverSig(originalApprovalLog.getApproverSig());
				approvalLog.setCallForward(originalApprovalLog.getCallForward());
				approvalLog.setRuleSource(originalApprovalLog.getRuleSource());
				approvalLog.setApproverNotes("");
				approvalLog.setFyiApprover(originalApprovalLog.getFyiApprover());
				approvalLog.setRequiredApprover(originalApprovalLog.getRequiredApprover());
				approvalLog.setExcludeLess(originalApprovalLog.getExcludeLess());
				approvalLog.setBackupApprover(originalApprovalLog.getBackupApprover());
				approvalLog.setAdvisor(originalApprovalLog.getAdvisor());
				approvalLog.setRecommendation(originalApprovalLog.getRecommendation());
				approvalLog.setComp_id(comp_id);
				toList.add(approvalLog) ;
			}
			result = toList ;
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
