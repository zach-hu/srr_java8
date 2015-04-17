package com.tsa.puridiom.apppool.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsa.puridiom.entity.*;
import java.util.Map;

public class AppPoolUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			AppPool appPool = (AppPool)incomingRequest.get("appPool");
			if (appPool == null)
			{
				throw new Exception ("AppPool was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(appPool);
		
			result = appPool;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}