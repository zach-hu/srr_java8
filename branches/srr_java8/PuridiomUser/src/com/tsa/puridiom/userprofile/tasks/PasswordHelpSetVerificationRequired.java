package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PasswordHelpSetVerificationRequired extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("verificationRequired", "true");
			
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