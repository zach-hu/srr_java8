package com.tsa.puridiom.supplierportal.authentication.tasks;

import com.tsa.puridiom.property.PropertiesManager;
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
			String	organizationId = (String) incomingRequest.get("organizationId");
			boolean caseSensitive = PropertiesManager.getInstance(organizationId).getProperty("MISC", "PassCaseSensitive", "N" ).equals("Y");

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "  ";
			}

			message = message + "  You have entered an invalid login id or password.";
			if(caseSensitive)
				message = message + " Password is case sensitive.";

			incomingRequest.put("errorMsg", message);
			incomingRequest.put("organizationId", organizationId);

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