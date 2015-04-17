package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserGetFromCache extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("userId");
			String organizationId = (String) incomingRequest.get("organizationId");
			
			RegisterUser user = VendorRegistrationUserManager.getInstance().getUserInCache(organizationId, userId);

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
