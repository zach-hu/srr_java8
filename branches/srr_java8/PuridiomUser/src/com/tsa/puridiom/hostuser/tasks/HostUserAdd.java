package com.tsa.puridiom.hostuser.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class HostUserAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		DBSession dbs = null;
		
		try
		{
		    HostUser hostUser = (HostUser)incomingRequest.get("hostUser");
			if (hostUser == null)
			{
				throw new Exception ("HostUser was not found.");
			}
		
			//	Always use host database configuration for this table
			dbs = new DBSession("host") ;		
			dbs.startTransaction();
			dbs.add(hostUser);
			dbs.commit();
			
			result = hostUser;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
		    if (dbs != null)
		    {
		        dbs.rollback();   
		    }			
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}