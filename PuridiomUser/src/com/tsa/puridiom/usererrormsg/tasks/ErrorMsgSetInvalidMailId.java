package com.tsa.puridiom.usererrormsg.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetInvalidMailId extends Task
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

			message = message + "  You have entered an invalid E-mail address.";

			incomingRequest.put("errorMsg", message);

			result = message;
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