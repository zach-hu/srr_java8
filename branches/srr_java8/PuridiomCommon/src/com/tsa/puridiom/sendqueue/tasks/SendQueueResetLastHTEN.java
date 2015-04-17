package com.tsa.puridiom.sendqueue.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueResetLastHTEN extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List sendqueueList = (List)incomingRequest.get("sendqueueList");
			if(sendqueueList == null){	sendqueueList = new ArrayList();	}
			if(sendqueueList.size() > 0)
			{
				SendQueue sendQueue = (SendQueue)sendqueueList.get(0);
				sendQueue.setStatus("00");
				incomingRequest.put("sendQueue", sendQueue);
			}
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("HTML email could not be sent!", e);
		}
		return super.executeTask(object);
	}

}
