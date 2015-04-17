package com.tsa.puridiom.receipt.html.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class AlertSetup extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			Object mainIc = incomingRequest.get("mainIc");
			incomingRequest.put("PoHeader_icPoHeader", mainIc.toString());
			System.out.println("mainic: " + mainIc);
			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return ret;
	}
}
