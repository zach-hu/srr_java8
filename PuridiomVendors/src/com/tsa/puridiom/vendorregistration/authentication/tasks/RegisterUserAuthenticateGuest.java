package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.*;

public class RegisterUserAuthenticateGuest extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            RegisterUser user = null;
			String	userId = (String) incomingRequest.get("userId");
			String	organizationId = (String) incomingRequest.get("organizationId");

			if (!Utility.isEmpty(organizationId) && !Utility.isEmpty(userId) && userId.equals("BB-GUEST"))
			{
				user = new RegisterUser();
				user.setEmailAddress(userId);
				user.setOrganizationId(organizationId);
				user.setGuest(true);
				user.setAuthenticated(true);
				user.setLoginAttempts(0);
				user.setLoginTime(new Date());
				user.setStatus("02");
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