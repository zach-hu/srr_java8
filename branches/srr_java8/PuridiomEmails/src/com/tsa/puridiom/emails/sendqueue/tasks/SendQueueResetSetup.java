package com.tsa.puridiom.emails.sendqueue.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueResetSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			SendQueue sendQueue = (SendQueue)incomingRequest.get("sendQueue");

			if(sendQueue == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("SendQueue needed!");
			}
			sendQueue.setStatus("00");
			sendQueue.setErrorText("");
			sendQueue.setAttempts(new BigDecimal(0));
			result = sendQueue;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueue coud not be reset!", e);
		}

		return result;
	}

}