package com.tsa.puridiom.apprule.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class AppRuleSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("AppRule_userId", "");
			incomingRequest.put("AppRule_amount", "0");
			incomingRequest.put("AppRule_udf1Code", " ");
			incomingRequest.put("AppRule_udf2Code", " " );
			incomingRequest.put("AppRule_udf3Code", " ");
			incomingRequest.put("AppRule_udf4Code", " ");
			incomingRequest.put("AppRule_udf5Code", " ");
			incomingRequest.put("AppRule_udf6Code", " ");
			incomingRequest.put("AppRule_udf7Code", " ");
			incomingRequest.put("AppRule_udf8Code", " ");
			incomingRequest.put("AppRule_udf9Code", " ");
			incomingRequest.put("AppRule_udf10Code", " ");
			incomingRequest.put("AppRule_approverLevel", "0");
			incomingRequest.put("AppRule_excludeLess", "0");
			incomingRequest.put("AppRule_fyiApprover", "");
			incomingRequest.put("AppRule_requiredApprover", "");
			incomingRequest.put("AppRule_notes", "");
			incomingRequest.put("AppRule_advisor", "");

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
