package com.tsa.puridiom.sendqueue.tasks;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class SendQueueDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		SendQueue sendQueue = (SendQueue)incomingRequest.get("sendQueue");
		if(sendQueue == null)
		{
			sendQueue = new SendQueue();
		}
		SendQueueSetValuesPK setValues = new SendQueueSetValuesPK();
		setValues.setValues(incomingRequest, sendQueue);
		dbs.delete(sendQueue);
		this.setStatus(dbs.getStatus()) ;
		return sendQueue ;
	}

}