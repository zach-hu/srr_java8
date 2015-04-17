package com.tsa.puridiom.services.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class SendQueueProcessListByAction extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			List sendqueueList = (List) incomingRequest.get("sendQueueList");
			String organizationId = (String)incomingRequest.get("organizationId");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess process = processLoader.loadProcess("sendqueue-process-by-action.xml");

			for(int i = 0; i < sendqueueList.size(); i++)
			{
				SendQueue sq = (SendQueue)sendqueueList.get(i);
				try
				{
					incomingRequest.put("sendQueue", sq);
					process.executeProcess(incomingRequest);
				}
				catch(Exception e)
				{
					Log.error(this, e.getMessage());
				}
			}
			this.setStatus(Status.SUCCEEDED) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueue list By Status could not be retrieved");
		}

		return ret ;
	}

}
