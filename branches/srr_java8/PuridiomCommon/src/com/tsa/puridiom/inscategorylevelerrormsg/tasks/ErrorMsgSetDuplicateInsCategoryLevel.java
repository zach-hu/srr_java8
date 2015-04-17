package com.tsa.puridiom.inscategorylevelerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class ErrorMsgSetDuplicateInsCategoryLevel extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String oid = (String)incomingRequest.get("organizationId");
			String message = "";

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "  This insurance category level code already exists.  Your information has not been saved.";

			incomingRequest.put("duplicateInsCategoryLevelErrorMsg", message);
			incomingRequest.put("newInsCategoryLevel", "Y");

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