package com.tsa.puridiom.vendorregistration.tasks;

import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RegisterUserSetVendorId extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RegisterUser registerUser = (RegisterUser) incomingRequest.get("registerUser");
			String	vendorId = (String) incomingRequest.get("vendorId");
			
			if (registerUser == null)
			{
				registerUser = new RegisterUser();
			}
			
			registerUser.setVendorId(vendorId);
			
			result = registerUser;
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