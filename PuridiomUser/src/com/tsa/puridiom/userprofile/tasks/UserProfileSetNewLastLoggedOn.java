package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class UserProfileSetNewLastLoggedOn extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
			if (userProfile != null)
			{
				/** Update based on user time zone **/
				String timeZone = userProfile.getTimeZone();
				userProfile.setLastLoggedOn(userProfile.getLoggedOn());
				userProfile.setLoggedOn(Dates.getCurrentDate(timeZone));
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