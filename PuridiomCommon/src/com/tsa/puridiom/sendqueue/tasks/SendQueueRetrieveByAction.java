package com.tsa.puridiom.sendqueue.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueRetrieveByAction extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from SendQueue as SendQueue where SendQueue.action = ?";
			String action = (String) incomingRequest.get("SendQueue_action");
			ret = dbs.query(queryString, new Object[] {action} , new Type[] { Hibernate.STRING}) ;
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueue list By Action could not be retrieved");
		}

		return ret ;
	}
}