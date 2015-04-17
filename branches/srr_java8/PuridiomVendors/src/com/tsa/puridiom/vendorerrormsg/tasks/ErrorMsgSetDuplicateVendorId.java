package com.tsa.puridiom.vendorerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class ErrorMsgSetDuplicateVendorId extends Task
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
			
			message = message + "  A record for this Vendor Id already exists.";
			
			incomingRequest.put("errorMsg", message);
			incomingRequest.put("newVendor", "Y");
			
			result = message;
			this.status = Status.COMPLETED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}