package com.tsa.puridiom.timer.tasks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class HiltonProcessSendQueue extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List sendqueueList = (List)incomingRequest.get("sendqueueList");
			for(int i = 0; i < sendqueueList.size(); i++)
			{
				SendQueue sendQueue = (SendQueue)sendqueueList.get(i);
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
				PuridiomProcess process = processLoader.loadProcess("puridiom-services-actions.xml");
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("userId", incomingRequest.get("userId"));
				newIncomingRequest.put("organizationId", incomingRequest.get("organizationId"));
				newIncomingRequest.put("sendQueue", sendQueue);
				try
				{
					process.executeProcess(incomingRequest);
				}
				catch (Exception e)
				{
					// TODO: handle exception when individual sendqueue record fails
				}
			}

			incomingRequest.put("SendQueue_status", "00");
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Timer could not be setup", e);
		}
		return super.executeTask(object);
	}

}
