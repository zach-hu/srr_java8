package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetPasswordResetRequired extends Task
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
			
			message = message + "  A password change is required.  Please enter a new password.";
			
			incomingRequest.put("resetPassword", "true");
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