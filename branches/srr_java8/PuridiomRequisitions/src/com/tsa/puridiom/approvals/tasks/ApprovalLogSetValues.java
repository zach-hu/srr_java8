package com.tsa.puridiom.approvals.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ApprovalLogSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ApprovalLogPK comp_id = new ApprovalLogPK();
			ApprovalLog approvalLog = (ApprovalLog) incomingRequest.get("approvalLog");
			if (approvalLog == null)
			{
				approvalLog = new ApprovalLog();
			}

			if (incomingRequest.containsKey("ApprovalLog_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("ApprovalLog_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("ApprovalLog_icLine"))
			{
				String icLineString = (String) incomingRequest.get("ApprovalLog_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("ApprovalLog_sequence"))
			{
				String sequenceString = (String) incomingRequest.get("ApprovalLog_sequence");
				if (Utility.isEmpty(sequenceString))
				{
					sequenceString = "0";
				}
				BigDecimal sequence = new BigDecimal ( sequenceString );
				comp_id.setSequence(sequence);
			}
			if (incomingRequest.containsKey("ApprovalLog_userId"))
			{
				String userId = (String) incomingRequest.get("ApprovalLog_userId");
				comp_id.setUserId(userId);
			}
			if (incomingRequest.containsKey("ApprovalLog_amount"))
			{
				String amountString = (String) incomingRequest.get("ApprovalLog_amount");
				if (Utility.isEmpty(amountString))
				{
					amountString = "0";
				}
				BigDecimal amount = new BigDecimal ( amountString );
				approvalLog.setAmount(amount);
			}
			if (incomingRequest.containsKey("ApprovalLog_approverAmount"))
			{
				String approverAmountString = (String) incomingRequest.get("ApprovalLog_approverAmount");
				if (Utility.isEmpty(approverAmountString))
				{
					approverAmountString = "0";
				}
				BigDecimal approverAmount = new BigDecimal ( approverAmountString );
				approvalLog.setApproverAmount(approverAmount);
			}
			if (incomingRequest.containsKey("ApprovalLog_approved"))
			{
				String approved = (String) incomingRequest.get("ApprovalLog_approved");
				approvalLog.setApproved(approved);
			}
			if (incomingRequest.containsKey("ApprovalLog_udfValues"))
			{
				String udfValues = (String) incomingRequest.get("ApprovalLog_udfValues");
				approvalLog.setUdfValues(udfValues);
			}
			if (incomingRequest.containsKey("ApprovalLog_authorized"))
			{
				String authorized = (String) incomingRequest.get("ApprovalLog_authorized");
				approvalLog.setAuthorized(authorized);
			}
			if (incomingRequest.containsKey("ApprovalLog_alternateUserid"))
			{
				String alternateUserid = (String) incomingRequest.get("ApprovalLog_alternateUserid");
				approvalLog.setAlternateUserid(alternateUserid);
			}
			if (incomingRequest.containsKey("ApprovalLog_dateAssigned"))
			{
				String dateAssignedString = (String) incomingRequest.get("ApprovalLog_dateAssigned");
				Date dateAssigned = Dates.getDate(dateAssignedString);
				approvalLog.setDateAssigned(dateAssigned);
			}
			if (incomingRequest.containsKey("ApprovalLog_dateApproved"))
			{
				String dateApprovedString = (String) incomingRequest.get("ApprovalLog_dateApproved");
				Date dateApproved = Dates.getDate(dateApprovedString);
				approvalLog.setDateApproved(dateApproved);
			}
			if (incomingRequest.containsKey("ApprovalLog_approverType"))
			{
				String approverType = (String) incomingRequest.get("ApprovalLog_approverType");
				approvalLog.setApproverType(approverType);
			}
			if (incomingRequest.containsKey("ApprovalLog_ruleType"))
			{
				String ruleType = (String) incomingRequest.get("ApprovalLog_ruleType");
				approvalLog.setRuleType(ruleType);
			}
			if (incomingRequest.containsKey("ApprovalLog_poolid"))
			{
				String poolid = (String) incomingRequest.get("ApprovalLog_poolid");
				approvalLog.setPoolid(poolid);
			}
			if (incomingRequest.containsKey("ApprovalLog_ruleAction"))
			{
				String ruleAction = (String) incomingRequest.get("ApprovalLog_ruleAction");
				approvalLog.setRuleAction(ruleAction);
			}
			if (incomingRequest.containsKey("ApprovalLog_approverSig"))
			{
				String approverSig = (String) incomingRequest.get("ApprovalLog_approverSig");
				approvalLog.setApproverSig(approverSig);
			}
			if (incomingRequest.containsKey("ApprovalLog_callForward"))
			{
				String callForward = (String) incomingRequest.get("ApprovalLog_callForward");
				approvalLog.setCallForward(callForward);
			}
			if (incomingRequest.containsKey("ApprovalLog_ruleSource"))
			{
				String ruleSource = (String) incomingRequest.get("ApprovalLog_ruleSource");
				approvalLog.setRuleSource(ruleSource);
			}
			if (incomingRequest.containsKey("ApprovalLog_approverNotes"))
			{
				String approverNotes = (String) incomingRequest.get("ApprovalLog_approverNotes");
				approvalLog.setApproverNotes(approverNotes);
			}
			if (incomingRequest.containsKey("ApprovalLog_fyiApprover"))
			{
				String fyiApprover = (String) incomingRequest.get("ApprovalLog_fyiApprover");
				approvalLog.setFyiApprover(fyiApprover);
			}
			if (incomingRequest.containsKey("ApprovalLog_requiredApprover"))
			{
				String requiredApprover = (String) incomingRequest.get("ApprovalLog_requiredApprover");
				approvalLog.setRequiredApprover(requiredApprover);
			}
			if (incomingRequest.containsKey("ApprovalLog_excludeLess"))
			{
				String excludeLessString = (String) incomingRequest.get("ApprovalLog_excludeLess");
				if (Utility.isEmpty(excludeLessString))
				{
					excludeLessString = "0";
				}
				BigDecimal excludeLess = new BigDecimal ( excludeLessString );
				approvalLog.setExcludeLess(excludeLess);
			}
			if (incomingRequest.containsKey("ApprovalLog_pooldesc"))
			{
				String poolDesc = (String) incomingRequest.get("ApprovalLog_pooldesc");
				approvalLog.setPooldesc(poolDesc);
			}
			if (incomingRequest.containsKey("ApprovalLog_backupApprover"))
			{
				String backupApprover = (String) incomingRequest.get("ApprovalLog_backupApprover");
				approvalLog.setBackupApprover(backupApprover);
			}
			if (incomingRequest.containsKey("ApprovalLog_advisor"))
			{
				String advisor = (String) incomingRequest.get("ApprovalLog_advisor");
				approvalLog.setAdvisor(advisor);
			}
			if (incomingRequest.containsKey("ApprovalLog_sendTo"))
			{
				String sendToString = (String) incomingRequest.get("ApprovalLog_sendTo");
				if (!Utility.isEmpty(sendToString)) {
				    if (sendToString.equalsIgnoreCase("Y")) {
				        approvalLog.setSendTo(true);
				    } else {
				        approvalLog.setSendTo(false);
				    }
				}
			}
			if (incomingRequest.containsKey("ApprovalLog_approverName"))
			{
			    // This value will not get updated into the database.  It is used for display purposes only
			    String approverName = (String) incomingRequest.get("ApprovalLog_approverName");
				approvalLog.setApproverName(approverName);
			}
			if (incomingRequest.containsKey("ApprovalLog_recommendation"))
			{
				String recommendation = (String) incomingRequest.get("ApprovalLog_recommendation");
				approvalLog.setRecommendation(recommendation);
			}
			if (incomingRequest.containsKey("ApprovalLog_keepApprover"))
			{
				String keepApprover = (String) incomingRequest.get("ApprovalLog_keepApprover");
				approvalLog.setKeepApprover(keepApprover);
			}
			if (incomingRequest.containsKey("ApprovalLog_keepApprovedFlag"))
			{
				String keepApprovedFlag = (String) incomingRequest.get("ApprovalLog_keepApprovedFlag");
				approvalLog.setKeepApprovedFlag(keepApprovedFlag);
			}
			if (incomingRequest.containsKey("ApprovalLog_keepDateAssigned"))
			{
				String keepDateAssignedString = (String) incomingRequest.get("ApprovalLog_keepDateAssigned");
				if(!keepDateAssignedString.trim().isEmpty())
				{
					Date dateAssigned = Dates.getDate(keepDateAssignedString);
					approvalLog.setDateAssigned(dateAssigned);
				}
			}
			if (incomingRequest.containsKey("ApprovalLog_keepDateApproved"))
			{
				String keepDateApprovedString = (String) incomingRequest.get("ApprovalLog_keepDateApproved");
				if(!keepDateApprovedString.trim().isEmpty())
				{
					Date dateApproved = Dates.getDate(keepDateApprovedString);
					approvalLog.setDateApproved(dateApproved);
				}
			}
			approvalLog.setComp_id(comp_id);

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
