package com.tsa.puridiom.vendorregistration.authentication.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class SetTermsAcceptanceRequired extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			incomingRequest.put("termsAcceptanceRequired", "true");

			result = "true";
			
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