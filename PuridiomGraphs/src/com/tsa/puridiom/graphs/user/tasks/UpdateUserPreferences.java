package com.tsa.puridiom.graphs.user.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class UpdateUserPreferences extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Map userIncomingRequest = new HashMap();
			String graphName = (String)incomingRequest.get("graphName");
			userIncomingRequest.put("UserPreference_userId", incomingRequest.get("organizationId"));
			userIncomingRequest.put("UserPreference_userId", incomingRequest.get("userId"));
			userIncomingRequest.put("UserPreference_property", graphName + "dashWhere");

			userIncomingRequest.put("UserPreference_value", incomingRequest.get(graphName + "_userFilter"));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}

		return ret;
	}
}
