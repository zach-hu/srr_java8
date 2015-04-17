/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.*;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author kelli
 */
public class UserProfileSetRegistered extends Task
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
				userProfile.setRegistered(true);
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
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#undoTask(java.lang.Object)
	 */
	public boolean undoTask(Object object) throws Exception
	{
		boolean success = false;
		try
		{
			Map incomingRequest = (Map) object;
			UserProfile userProfile = (UserProfile) incomingRequest.get("userProfile");
			
			if (userProfile != null && !HiltonUtility.isEmpty(userProfile.getUserId()))
			{	
				userProfile.setRegistered(false);
			}
			
			incomingRequest.put("userProfile", userProfile);
			
			object = incomingRequest;
		}
		catch (Exception e)
		{
			//handle exception by sending it to the
			//exception handling service
			//no need to throw this since the process
			//has exited at this point
		}
		finally
		{
			return success;
		}
	}

}
