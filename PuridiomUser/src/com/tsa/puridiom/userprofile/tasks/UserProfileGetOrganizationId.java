/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author kelli
 */
public class UserProfileGetOrganizationId extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String	organizationId = "";
		try
		{
			UserProfile userProfile = userProfile = (UserProfile) incomingRequest.get("userProfile");
			
			if (userProfile != null)
			{	
				organizationId = userProfile.getOrganizationId();
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return organizationId;
	}
}
