package com.tsa.puridiom.resetpassword;

import com.tsa.puridiom.vendorregistration.RegisterUser;
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
			RegisterUser user = (RegisterUser) incomingRequest.get("registerUser");
			String userId = user.getVendorId();
			String mailId = user.getEmailAddress();

			if (Utility.isEmpty(mailId)){
				mailId = (String) incomingRequest.get("UserProfile_mailId");
			}
			if (Utility.isEmpty(mailId)){
				mailId = (String) incomingRequest.get("loginId");
			}

			incomingRequest.put("ResetPasswordLink_userId", userId);
			incomingRequest.put("ResetPasswordLink_mailId", mailId);
			incomingRequest.put("UserProfile_userId", userId);
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