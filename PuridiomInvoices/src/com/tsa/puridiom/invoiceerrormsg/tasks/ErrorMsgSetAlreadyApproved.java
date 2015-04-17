package com.tsa.puridiom.invoiceerrormsg.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ErrorMsgSetAlreadyApproved extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";

			if (incomingRequest.containsKey("errorMsg"))
			{
				message = (String)incomingRequest.get("errorMsg") + " ";
			}

			message = "You have already approved this invoice.";
			incomingRequest.put("alreadyApprovedErrorMsg", message);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "Could not set error message!");
			e.printStackTrace();
		}

		return result;
	}
}
