package com.tsa.puridiom.alerts.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AlertsGetAttachment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;

		try
		{
			if(incomingRequest.containsKey("report"))
			{
				incomingRequest.put("SendQueue_attachment", incomingRequest.get("report"));
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertGetProcessFromType failed!" + e.getMessage(), e);
		}
		return ret;
	}
}
