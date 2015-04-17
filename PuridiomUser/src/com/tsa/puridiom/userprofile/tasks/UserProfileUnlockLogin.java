/*
 * Created on August 4, 2005
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * @author kelli
 */
public class UserProfileUnlockLogin extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		UserProfile userProfile = null;

		try
		{
			userProfile = (UserProfile) incomingRequest.get("userProfile");

			if (userProfile != null && !HiltonUtility.isEmpty(userProfile.getUserId()))
			{
				userProfile.setLockLogin("N");
				userProfile.setLockOutCounter(new BigDecimal(0));
			}

			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return userProfile;
	}
}
