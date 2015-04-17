package com.tsa.puridiom.sendqueue.tasks;

import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueRetrieveByDocic extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			StringBuffer queryString = new StringBuffer("from SendQueue as sendqueue where 1=1 ");
			if(incomingRequest.containsKey("SendQueue_status"))
			{			
				String status = (String) incomingRequest.get("SendQueue_status");
				queryString.append(" AND sendqueue.status = '" + status + "'");
			}
			ret = dbs.query(queryString.toString()) ;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueue list By Status could not be retrieved");
		}
		
		return ret ;
	}
}