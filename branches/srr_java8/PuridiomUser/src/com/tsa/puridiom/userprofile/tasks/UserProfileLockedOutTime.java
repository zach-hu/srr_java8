/*
 * Created on July 11, 2011
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;

/**
 * @author kelli
 */
public class UserProfileLockedOutTime extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			UserProfile user = (UserProfile) incomingRequest.get("userProfile");

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Date currentTime = new Date();
			incomingRequest.put("Current_time", dateFormat.format(currentTime));

			Date lockedOutTime	= user.getLockedOutTime();

			if (lockedOutTime == null) {
				lockedOutTime = new Date(72, 8, 13);
			}
			Date renewTime		= (Date) lockedOutTime.clone();

			int number	= lockedOutTime.getHours();
			if (number == 23)
				renewTime.setHours(0);
			else
				renewTime.setHours(number + 1);

			result = dateFormat.format(renewTime);
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return result;
		}
	}

}
