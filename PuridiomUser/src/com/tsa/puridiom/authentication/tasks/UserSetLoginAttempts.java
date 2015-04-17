package com.tsa.puridiom.authentication.tasks;

import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;
import java.math.BigDecimal;

public class UserSetLoginAttempts extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserProfile user = (UserProfile) incomingRequest.get("userProfile");
			BigDecimal	attempts	= user.getLockOutCounter();
			BigDecimal	one			= new BigDecimal(1);

			if (user != null){
				if (attempts != null) {
					user.setLockOutCounter(attempts.add(one));
					incomingRequest.put("loginCounter", attempts.add(one).intValue());
				}
				else {
					user.setLockOutCounter(one);
					incomingRequest.put("loginCounter", one.intValue());
				}
			}

			String timeZone = user.getTimeZone();
			user.setLockedOutTime(Dates.getCurrentDate(timeZone));

			incomingRequest.put("UserProfile_OID", user.getOrganizationId());
			result = user;
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