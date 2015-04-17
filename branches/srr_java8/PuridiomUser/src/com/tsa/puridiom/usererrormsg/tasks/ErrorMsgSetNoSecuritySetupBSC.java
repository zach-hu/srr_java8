package com.tsa.puridiom.usererrormsg.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetNoSecuritySetupBSC extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			String message = "";
			
			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "<br>";
			}
			
			message = message + "  You have not set up a security question.<br>";
			message = message + "  Contact the Puridiom Help Desk at blueshield@puridiom.com to reset your password.";
			
			incomingRequest.put("returnToLogin", "true");
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