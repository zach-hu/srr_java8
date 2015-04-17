package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.poheader.history.tasks.PoHeaderHistory;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if (poHeader == null)
			{
				throw new Exception ("PoHeader was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			
			dbs.add(poHeader);
			
			result = poHeader;
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