package com.tsa.puridiom.vendorregistration.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetInvalidEin extends Task
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
			
			message = message + "  You have entered an invalid EIN Number and therefore, cannot be registered at this time.";
			
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