package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetFinalLoginAttempt extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	organizationId = (String) incomingRequest.get("organizationId");

			String message	= ("Our apologies, but your account is currently locked.  Please contact your " + organizationId + " representative for assisstance. Thank you.");

			incomingRequest.put("finalLoginAttempt", "true");
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