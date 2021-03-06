/*
 * Created on June 10, 2004
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.usermanager.UserRole;
import com.tsa.puridiom.usermanager.UserRoleManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class UserRoleSetInCache extends Task
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
			UserRole userRole = (UserRole) incomingRequest.get("userRole");
			
			if (userRole != null)
			{
				UserRoleManager.getInstance().setUserRoleInCache(userRole);
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
