package com.tsa.puridiom.apppooluser.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class AppPooluserAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			AppPooluser appPooluser = (AppPooluser)incomingRequest.get("appPooluser");
			if (appPooluser == null)
			{
				throw new Exception ("AppPooluser was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(appPooluser);
		
			result = appPooluser;
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