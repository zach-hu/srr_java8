package com.tsa.puridiom.posecurity.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoSecurity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoSecurityUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			PoSecurity poSecurity = (PoSecurity)incomingRequest.get("poSecurity");
			if (poSecurity == null)
			{
				throw new Exception ("PoSecurity was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(poSecurity);
		
			result = poSecurity;
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