package com.tsa.puridiom.apppool;

import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.apppool.AppPoolManager;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class AppPoolManagerSetAppPoolInCache extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			AppPool appPool = (AppPool) incomingRequest.get("appPool");

			if (appPool != null)
			{
				AppPoolManager.getInstance().setAppPoolInCache(organizationId, appPool);
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return null;
		}
	}
}
