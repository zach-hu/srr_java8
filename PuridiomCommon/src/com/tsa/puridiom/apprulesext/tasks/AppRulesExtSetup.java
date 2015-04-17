package com.tsa.puridiom.apprulesext.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class AppRulesExtSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("AppRulesExt_ruleNumber", "0");
			incomingRequest.put("AppRulesExt_ruleType", "");
			incomingRequest.put("AppRulesExt_ruleOrder", "0");
			incomingRequest.put("AppRulesExt_ruleFilename", "");
			incomingRequest.put("AppRulesExt_routto", "");
			incomingRequest.put("AppRulesExt_ruleAction", "");
			incomingRequest.put("AppRulesExt_ruleText", "");
			incomingRequest.put("AppRulesExt_notes", "");
			incomingRequest.put("AppRulesExt_approverAmount", "0");
			incomingRequest.put("AppRulesExt_requiredAuthority", "0");
			incomingRequest.put("AppRulesExt_reqdAuthObject", "");
			incomingRequest.put("AppRulesExt_reqdAuthName", "");
			incomingRequest.put("AppRulesExt_reqdAuthSource", "");
			incomingRequest.put("AppRulesExt_reqdAuthValue", "");
			incomingRequest.put("AppRulesExt_fyiApprover", "");
			incomingRequest.put("AppRulesExt_requiredApprover", "");
			incomingRequest.put("AppRulesExt_advisor", "");
			incomingRequest.put("AppRulesExt_superRule", "");
			incomingRequest.put("AppRulesExt_enabled", "");

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
