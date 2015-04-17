/*
 * Created on June 21, 2004
 */
package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author kelli
 */
public class UserProfileRetrieveSetup extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try 
		{
			String	userId = (String) incomingRequest.get("userId");
			String	organizationId = (String) incomingRequest.get("organizationId");
			
			incomingRequest.put("UserProfile_userId", userId);
			incomingRequest.put("UserProfile_organizationId", organizationId);
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return null;
	}
}
