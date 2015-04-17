package com.tsa.puridiom.resetpasswordlink.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

public class ResetPasswordLinkCreateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String userId = (String) incomingRequest.get("UserProfile_userId");
			String mailId = (String) incomingRequest.get("HostUser_mailId");
			
			if (Utility.isEmpty(mailId)){
				mailId = (String) incomingRequest.get("UserProfile_mailId");
			}
			if (Utility.isEmpty(mailId)){
				mailId = (String) incomingRequest.get("loginId");
			}
			
			incomingRequest.put("ResetPasswordLink_userId", userId);		
			incomingRequest.put("ResetPasswordLink_mailId", mailId);			
		
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