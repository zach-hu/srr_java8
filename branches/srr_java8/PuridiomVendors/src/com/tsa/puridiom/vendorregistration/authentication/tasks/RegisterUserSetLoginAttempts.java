package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserSetLoginAttempts extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			
			if (user == null)
			{
				String userId = (String) incomingRequest.get("userId");
				String organizationId = (String) incomingRequest.get("organizationId");
				
				user = new RegisterUser();
				user.setEmailAddress(userId);
				user.setOrganizationId(organizationId);
			}
			
			user.setLoginAttempts(user.getLoginAttempts() + 1);

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