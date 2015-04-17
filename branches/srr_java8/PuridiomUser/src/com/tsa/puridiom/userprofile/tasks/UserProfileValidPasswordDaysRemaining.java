/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kelli
 */
public class UserProfileValidPasswordDaysRemaining extends Task
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
				return null;
			}
			else
			{
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String passChanged = userProfile.getPassChanged();
				
				try
				{
					passChanged = formatter.format(passChanged);					
				}
				catch (IllegalArgumentException e)
				{
					// Not a valid date format
					incomingRequest.put("resetPassword", "false");
					
					return null;
				}
				int	daysValid = Integer.valueOf(PropertiesManager.getInstance(userProfile.getOrganizationId()).getProperty("MISC", "daysValid", "30")).intValue();				
				daysAfter = new Integer(Dates.getDaysAfter(passChanged, "", (daysValid +1)));
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return daysAfter;
		}
	}

}
