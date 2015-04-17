/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kelli
 */
public class UserProfileDaysAfterPasswordSet extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Integer	daysAfter = new Integer(0);

		try
		{
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");

			if (userProfile == null)
			{
				return daysAfter;
			}
			else
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String passChanged = userProfile.getPassChanged();

				try
				{
					passChanged = formatter.format(Dates.getSqlDate(passChanged));
				}
				catch (IllegalArgumentException e)
				{
					// Not a valid date format
					return daysAfter;
				}
				int	daysValid = 0;
				try
				{
					daysValid = (Integer.valueOf(PropertiesManager.getInstance(userProfile.getOrganizationId()).getProperty("MISC", "daysValid", "30"))).intValue();

				}
				catch (NumberFormatException ne)
				{
					daysValid = 0;
				}
				daysAfter = new Integer(Dates.getDaysAfter(passChanged, "", daysValid + 1));
			}

			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}

		return daysAfter;
	}

}
