package com.tsa.puridiom.sendalert.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.Map;

public class SendAlertRetrieveByStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String status = (String) incomingRequest.get("SendAlert_status");

			String queryString = "from SendAlert as SendAlert where SendAlert.status = ? ";
			result = dbs.query(queryString, new Object[] {status } , new Type[] { Hibernate.STRING}) ;

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