/*
 * Created on August 9, 2004
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class UserManagerRemoveUserFromCache extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			String	userId = (String) incomingRequest.get("UserProfile_userId");
			String	organizationId = (String) incomingRequest.get("UserProfile_organizationId");

			UserManager.getInstance().removeUserFromCache(organizationId, userId);
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return null;
		}
	}

}
