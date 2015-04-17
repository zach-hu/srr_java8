package com.tsa.puridiom.sendqueue.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class SendQueueSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain sendQueue */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			SendQueue	originalSendQueue = (SendQueue) incomingRequest.get("sendQueue");
			SendQueue	sendQueue = new SendQueue();

			sendQueue.setQueueid(originalSendQueue.getQueueid());
			sendQueue.setDoctype(originalSendQueue.getDoctype());
			sendQueue.setDocic(originalSendQueue.getDocic());
			sendQueue.setSubject(originalSendQueue.getSubject());
			sendQueue.setMessagetext(originalSendQueue.getMessagetext());
			sendQueue.setOwner(originalSendQueue.getOwner());
			sendQueue.setSendfromtype(originalSendQueue.getSendfromtype());
			sendQueue.setSendfrom(originalSendQueue.getSendfrom());
			sendQueue.setSendtotype(originalSendQueue.getSendtotype());
			sendQueue.setSendto(originalSendQueue.getSendto());
			sendQueue.setStatus(originalSendQueue.getStatus());
			sendQueue.setDateadded(originalSendQueue.getDateadded());
			sendQueue.setTimeadded(originalSendQueue.getTimeadded());
			sendQueue.setAction(originalSendQueue.getAction());
			sendQueue.setDatesent(originalSendQueue.getDatesent());
			sendQueue.setTimesent(originalSendQueue.getTimesent());
			sendQueue.setAttachment(originalSendQueue.getAttachment());
			sendQueue.setVendorId(originalSendQueue.getVendorId());
			sendQueue.setAttempts(originalSendQueue.getAttempts());

			incomingRequest.put("sendQueue", sendQueue);

			SendQueueAdd addTask = new SendQueueAdd();
			sendQueue = (SendQueue) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = sendQueue;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}