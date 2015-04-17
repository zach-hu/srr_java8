package com.tsa.puridiom.usererrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetDuplicateMailId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "  An account for this Email address already exists.";

			incomingRequest.put("errorMsg", message);

			result = message;
			// Set status to failed so database transactions are not commited
			this.status = Status.FAILED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}