package com.tsa.puridiom.inscategorylevel.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InsCategoryLevelAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InsCategoryLevel insCategoryLevel = (InsCategoryLevel)incomingRequest.get("insCategoryLevel");
			if (insCategoryLevel == null)
			{
				throw new Exception ("Insurance Category Level was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(insCategoryLevel);
		
			result = insCategoryLevel;
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