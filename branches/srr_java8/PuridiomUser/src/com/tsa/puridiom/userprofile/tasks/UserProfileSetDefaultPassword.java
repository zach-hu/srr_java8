/*
 * Created on August 22, 2005
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author kelli
 */
public class UserProfileSetDefaultPassword extends Task
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
				userProfile.setUserPassword(BlackBox.getEncrypt("WELCOME1"));
				userProfile.setPassChanged("ADMIN");
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
