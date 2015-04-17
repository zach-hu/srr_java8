package com.tsa.puridiom.apprule.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import java.util.Map;

public class AppRuleDeleteByOriginalIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			incomingRequest.put("AppRule_userId", incomingRequest.get("OriginalApprover_userId"));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}