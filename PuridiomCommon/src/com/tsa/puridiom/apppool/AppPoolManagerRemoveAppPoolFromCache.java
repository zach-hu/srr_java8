package com.tsa.puridiom.apppool;

import com.tsa.puridiom.apppool.AppPoolManager;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class AppPoolManagerRemoveAppPoolFromCache extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			String poolid = (String) incomingRequest.get("AppPool_poolid");
			String organizationId = (String) incomingRequest.get("organizationId");

			AppPoolManager.getInstance().removeAppPoolFromCache(organizationId, poolid);
			
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
