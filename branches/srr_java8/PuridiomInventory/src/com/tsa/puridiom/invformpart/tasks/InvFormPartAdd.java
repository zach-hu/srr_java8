package com.tsa.puridiom.invformpart.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvFormPart;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvFormPartAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvFormPart invFormPart = (InvFormPart)incomingRequest.get("invFormPart");
			if (invFormPart == null)
			{
				throw new Exception ("InvFormPart was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(invFormPart);
		
			result = invFormPart;
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