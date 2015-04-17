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
 * @author nestor
 */
public class UserProfileWarningRemainingDaysPasswordSet extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Integer	remainingDays = new Integer(0);

		try
		{
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");

			if (userProfile == null)
			{
				return remainingDays;
			}
			else
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String passChanged = userProfile.getPassChanged();
				String expirationDate = "";

				try
				{
					passChanged = formatter.format(Dates.getSqlDate(passChanged));
				}
				catch (IllegalArgumentException e)
				{
					// Not a valid date format
					return remainingDays;
				}
				int	daysValid = 0;
				try
				{
					daysValid = (Integer.valueOf(PropertiesManager.getInstance(userProfile.getOrganizationId()).getProperty("MISC", "daysValid", "30"))).intValue();
					expirationDate = formatter.format(Dates.add(Dates.getSqlDate(passChanged), daysValid + 1));
				}
				catch (NumberFormatException ne)
				{
					daysValid = 0;
				}
				remainingDays = new Integer(Dates.getDaysAfter("", expirationDate, 0));
			}

			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}

		return remainingDays;
	}

}
