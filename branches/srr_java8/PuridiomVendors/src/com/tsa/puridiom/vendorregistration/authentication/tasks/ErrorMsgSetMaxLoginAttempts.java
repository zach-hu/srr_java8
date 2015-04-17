package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetMaxLoginAttempts extends Task
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
			
			if (!Utility.isEmpty(message)) {
			    message = message + "<br>";
			}
			message = message + "You have reached the maximum number of login attempts.";
			
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