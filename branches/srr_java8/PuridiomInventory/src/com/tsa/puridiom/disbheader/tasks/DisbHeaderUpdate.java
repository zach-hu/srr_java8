package com.tsa.puridiom.disbheader.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DisbHeaderUpdate extends Task
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
			dbs.update(disbHeader);
		
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