package com.tsa.puridiom.sendqueue.tasks;

import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueRetrieveByStatus extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String status = (String) incomingRequest.get("SendQueue_status");
			if(HiltonUtility.isEmpty(status))
			{
				status = DocumentStatus.SENDQUEUE_NEW;
			}

			String queryString = "from SendQueue as SendQueue where SendQueue.status = ? ";
			ret = dbs.query(queryString, new Object[] {status} , new Type[] { Hibernate.STRING}) ;

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