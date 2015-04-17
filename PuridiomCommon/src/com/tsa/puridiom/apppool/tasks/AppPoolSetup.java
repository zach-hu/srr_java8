package com.tsa.puridiom.apppool.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;


public class AppPoolSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("AppPool_poolid", "");
			incomingRequest.put("AppPool_pooldesc", "");
			incomingRequest.put("AppPool_poolflag1", "");
			incomingRequest.put("AppPool_poolflag2", "");

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