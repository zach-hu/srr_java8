package com.tsa.puridiom.graphs.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class GraphSaveUserPreference extends Task {

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret  =null;
		try
		{
			String graphName = (String)incomingRequest.get("graphName");
			String value = (String)incomingRequest.get("userFilter");

			incomingRequest.put("UserPreference_userId", incomingRequest.get("userId"));
			incomingRequest.put("UserPreference_property", (graphName + "dashWhere").toUpperCase());
			incomingRequest.put("UserPreference_value", value);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		// TODO Auto-generated method stub
		return ret;
	}

}
