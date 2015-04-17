package com.tsa.puridiom.authentication.tasks;

import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;
import java.math.BigDecimal;

public class UserSetLoginAttemptsToDefault extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserProfile user = (UserProfile) incomingRequest.get("userProfile");

			if (user.getLockOutCounter() != new BigDecimal(0)){
				user.setLockOutCounter(new BigDecimal(0));
			}

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