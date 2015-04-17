/*
 * Created on January 20, 2005
 */
package com.tsa.puridiom.userpreference.tasks;

import com.tsa.puridiom.entity.UserPreference;
import com.tsa.puridiom.userpreference.UserPreferenceManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UserPreferenceSetInCache extends Task
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
			String	organizationId = (String) incomingRequest.get("organizationId");
			UserPreference userPreference = (UserPreference) incomingRequest.get("userPreference");
			
			if (userPreference != null)
			{
				UserPreferenceManager.getInstance().setUserPreferenceInCache(organizationId, userPreference);
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
			return result;
		}
	}

}
