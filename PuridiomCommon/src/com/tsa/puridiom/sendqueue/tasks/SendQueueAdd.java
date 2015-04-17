package com.tsa.puridiom.sendqueue.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SendQueueAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			SendQueue sendQueue = (SendQueue)incomingRequest.get("sendQueue");
			if (sendQueue == null)
			{
				throw new Exception ("SendQueue was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.add(sendQueue);

			result = sendQueue;
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