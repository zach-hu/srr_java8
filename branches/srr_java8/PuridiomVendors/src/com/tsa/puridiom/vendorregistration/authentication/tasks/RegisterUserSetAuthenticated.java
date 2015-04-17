package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserSetAuthenticated extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			
			if (user != null)
			{
				user.setAuthenticated(true);
			}

			result = user;
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