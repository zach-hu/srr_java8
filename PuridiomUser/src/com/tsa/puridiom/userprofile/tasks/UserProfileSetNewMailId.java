package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UserProfileSetNewMailId extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
			if (userProfile == null)
			{
				userProfile = new UserProfile();
			}

			if (incomingRequest.containsKey("newUserProfile_mailId"))
			{
				String mailId = (String ) incomingRequest.get("newUserProfile_mailId");
				userProfile.setMailId(Utility.ckNull(mailId).toLowerCase());
			}

			result = userProfile;
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