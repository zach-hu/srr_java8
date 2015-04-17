package com.tsa.puridiom.approvals.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AppColumnsRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString =  "SELECT c.id.colsection, c.id.colname, c.coltype, c.coldesc " +
			 	"FROM AppColumn c  WHERE (c.id.formtype = 'REQ')";
			
			result = dbs.query(queryString) ;
					
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