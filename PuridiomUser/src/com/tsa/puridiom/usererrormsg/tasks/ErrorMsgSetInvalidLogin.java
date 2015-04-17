package com.tsa.puridiom.usererrormsg.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetInvalidLogin extends Task
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

			message = message + "  You have entered an invalid login id or password.";

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("UserLog_status", DocumentStatus.USERLOG_INVALID_PASSWORD);

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