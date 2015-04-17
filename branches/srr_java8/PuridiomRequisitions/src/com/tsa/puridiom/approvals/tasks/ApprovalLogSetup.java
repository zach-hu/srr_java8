package com.tsa.puridiom.approvals.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ApprovalLogSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("ApprovalLog_icHeader", "0");
			incomingRequest.put("ApprovalLog_icLine", "0");
			incomingRequest.put("ApprovalLog_sequence", "0");
			incomingRequest.put("ApprovalLog_userId", "");
			incomingRequest.put("ApprovalLog_amount", "0");
			incomingRequest.put("ApprovalLog_approverAmount", "0");
			incomingRequest.put("ApprovalLog_approved", "");
			incomingRequest.put("ApprovalLog_udfValues", "");
			incomingRequest.put("ApprovalLog_authorized", "");
			incomingRequest.put("ApprovalLog_alternateUserid", "");
			incomingRequest.put("ApprovalLog_dateAssigned", Dates.today("", userTimeZone));
			incomingRequest.put("ApprovalLog_dateApproved", Dates.today("", userTimeZone));
			incomingRequest.put("ApprovalLog_approverType", "");
			incomingRequest.put("ApprovalLog_ruleType", "");
			incomingRequest.put("ApprovalLog_poolid", "");
			incomingRequest.put("ApprovalLog_ruleAction", "");
			incomingRequest.put("ApprovalLog_approverSig", "");
			incomingRequest.put("ApprovalLog_callForward", "");
			incomingRequest.put("ApprovalLog_ruleSource", "");
			incomingRequest.put("ApprovalLog_approverNotes", "");
			incomingRequest.put("ApprovalLog_fyiApprover", "");
			incomingRequest.put("ApprovalLog_requiredApprover", "");
			incomingRequest.put("ApprovalLog_minApproverAmount", "0");
			incomingRequest.put("ApprovalLog_pooldesc", "");
			incomingRequest.put("ApprovalLog_backupApprover", "");
			incomingRequest.put("ApprovalLog_advisor", "");
			incomingRequest.put("ApprovalLog_recommendation", "");

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
