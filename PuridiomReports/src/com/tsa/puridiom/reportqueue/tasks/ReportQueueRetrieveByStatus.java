package com.tsa.puridiom.reportqueue.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReportQueueRetrieveByStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from ReportQueue as ReportQueue where ReportQueue.status = ?";
			String status = (String) incomingRequest.get("ReportQueue_status");
			ret = dbs.query(queryString, new Object[] {status} , new Type[] { Hibernate.STRING}) ;
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ReportQueue list By Status could not be retrieved. -" + e.getMessage());
		}

		return ret ;
	}
}