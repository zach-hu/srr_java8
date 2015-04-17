package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class UserProfileValidateMailIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	mailId = (String) incomingRequest.get("UserProfile_mailId");

			// Set HostUser_mailId and newMailId variables for vaildation
			incomingRequest.put("newMailId", mailId);
			incomingRequest.put("HostUser_mailId", mailId);

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