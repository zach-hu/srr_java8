package com.tsa.puridiom.apppool.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;


public class AppPoolSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			AppPool appPool = (AppPool) incomingRequest.get("appPool");
			if (appPool == null)
			{
				appPool = new AppPool();
			}

			if (incomingRequest.containsKey("AppPool_poolid"))
			{
				String poolid = (String ) incomingRequest.get("AppPool_poolid");
				appPool.setPoolid(poolid);
			}
			if (incomingRequest.containsKey("AppPool_pooldesc"))
			{
				String pooldesc = (String ) incomingRequest.get("AppPool_pooldesc");
				appPool.setPooldesc(pooldesc);
			}
			if (incomingRequest.containsKey("AppPool_poolflag1"))
			{
				String poolflag1 = (String ) incomingRequest.get("AppPool_poolflag1");
				appPool.setPoolflag1(poolflag1);
			}
			if (incomingRequest.containsKey("AppPool_poolflag2"))
			{
				String poolflag2 = (String ) incomingRequest.get("AppPool_poolflag2");
				appPool.setPoolflag2(poolflag2);
			}
			if (incomingRequest.containsKey("AppPool_poolType"))
			{
				String poolType = (String ) incomingRequest.get("AppPool_poolType");
				appPool.setPoolType(poolType);
			}

			result = appPool;
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