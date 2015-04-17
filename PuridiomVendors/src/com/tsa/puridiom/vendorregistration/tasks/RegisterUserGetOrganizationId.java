package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RegisterUserGetOrganizationId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");
			if (registerUser != null)
			{
			    result = registerUser.getOrganizationId();
			}
			else if (incomingRequest.containsKey("oid"))
			{
			    result = (String) incomingRequest.get("oid");
			}

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