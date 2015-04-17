package com.tsa.puridiom.autogen.tasks;

import com.tsa.puridiom.entity.AutoGen;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class AutoGenUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			AutoGen autoGen = (AutoGen)incomingRequest.get("autoGen");
			if (autoGen == null)
			{
				throw new Exception ("AutoGen was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(autoGen);
		
			result = autoGen;
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