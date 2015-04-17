package com.tsa.puridiom.sendqueue.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueRetrieveByActionDate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from SendQueue as SendQueue where SendQueue.action = ? AND SendQueue.dateadded = ?";
			String action = (String) incomingRequest.get("SendQueue_action");
			String dateadded = (String) incomingRequest.get("SendQueue_dateadded");
			ret = dbs.query(queryString, new Object[] {action, dateadded} , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueue list By ActionDate could not be retrieved");
		}

		return ret ;
	}
}