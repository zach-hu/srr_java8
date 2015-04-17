package com.tsa.puridiom.disbheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.DisbHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class DisbHeaderAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DisbHeader disbHeader = (DisbHeader)incomingRequest.get("disbHeader");
			if (disbHeader == null)
			{
				throw new Exception ("DisbHeader was not found.");
			}
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(disbHeader);
			
			result = disbHeader;
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