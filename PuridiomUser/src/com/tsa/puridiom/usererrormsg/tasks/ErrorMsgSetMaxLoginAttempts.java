package com.tsa.puridiom.usererrormsg.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ErrorMsgSetMaxLoginAttempts extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String message = "";
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");

			if (incomingRequest.containsKey("errorMsg")) {
				message = (String) incomingRequest.get("errorMsg") + "<br>";
			}

			if (!userProfile.getUserId().equalsIgnoreCase("SYSADM")){
				message = message + "You have reached the maximum number of login attempts.  Your account has been locked.";
				incomingRequest.put("UserLog_status", DocumentStatus.USERLOG_INVALID_PASSWORD);
				incomingRequest.put("errorMsg", message);
			}

			result = message;
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