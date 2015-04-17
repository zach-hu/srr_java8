package com.tsa.puridiom.currcodeerrormsg.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class ErrorMsgSetDuplicateCurrCode extends Task
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

			message = message + "  This currency code already exists.  Your information has not been saved.";

			incomingRequest.put("duplicateCurrCodeErrorMsg", message);
			incomingRequest.put("newCurrCode", "Y");

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