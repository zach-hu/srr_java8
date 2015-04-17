package com.tsa.puridiom.graphs.tasks;

import java.util.Map;

import org.hibernate.exception.*;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class GraphSaveViewUserPreference extends Task {

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret  =null;
		try
		{
			String graphName = (String)incomingRequest.get("graphName");
			String prefValue = (String)incomingRequest.get("prefValue");
			String prefName = (String)incomingRequest.get("prefName");

			incomingRequest.put("UserPreference_userId", incomingRequest.get("userId"));
			incomingRequest.put("UserPreference_property", prefName.toUpperCase());
			incomingRequest.put("UserPreference_value", prefValue);
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
