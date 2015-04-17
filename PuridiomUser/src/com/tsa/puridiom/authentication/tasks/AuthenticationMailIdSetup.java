package com.tsa.puridiom.authentication.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class AuthenticationMailIdSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	mailId = (String) incomingRequest.get("mailId");

			if (!Utility.isEmpty(mailId)) {
			    mailId = mailId.toLowerCase().trim();
			}

			incomingRequest.put("HostUser_mailId", mailId);
			incomingRequest.put("UserProfile_mailId", mailId);

			if (mailId != null && mailId.indexOf("@") < 0) {
				incomingRequest.put("HostUser_userId", mailId.toUpperCase());
				incomingRequest.put("UserProfile_userId", mailId.toUpperCase());
				String oId = (String) incomingRequest.get("organizationId") ;
				if (oId != null) {
					if (oId.trim().length() > 0) {
						incomingRequest.put("HostUser_organizationId", oId) ;
					}
				}
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