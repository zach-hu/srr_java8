/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.*;

/**
 * @author kelli
 */
public class UserProfileLockLogin extends Task
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
				if (!"Y".equals(userProfile.getLockLogin()) && !userProfile.getUserId().equalsIgnoreCase("SYSADM")){
					userProfile.setLockLogin("Y");
					String timeZone = userProfile.getTimeZone();
					userProfile.setLockedOutTime(Dates.getCurrentDate(timeZone));
				}
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
