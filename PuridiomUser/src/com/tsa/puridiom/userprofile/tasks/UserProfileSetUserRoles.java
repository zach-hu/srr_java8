/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

/**
 * @author kelli
 */
public class UserProfileSetUserRoles extends Task
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
		    String	organizationId = (String) incomingRequest.get("organizationId");
			userProfile = (UserProfile) incomingRequest.get("userProfile");
			
			if (userProfile != null && !HiltonUtility.isEmpty(userProfile.getUserId()))
			{
			    List userGroupRelList = (List) incomingRequest.get("userGroupRelList");
			    
			    if (userGroupRelList != null)
				{
			        UserManager.getInstance().setUserRoles(organizationId, userProfile, userGroupRelList);
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
