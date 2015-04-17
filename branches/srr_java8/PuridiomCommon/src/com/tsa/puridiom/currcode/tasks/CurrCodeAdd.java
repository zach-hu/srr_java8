package com.tsa.puridiom.currcode.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.CurrCode;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;


public class CurrCodeAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			CurrCode currCode = (CurrCode)incomingRequest.get("currCode");
			if (currCode == null)
			{
				throw new Exception ("CurrCode was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(currCode);
		
			result = currCode;
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