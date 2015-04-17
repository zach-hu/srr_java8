/*
 * Created on June 10, 2004
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.usermanager.UserRoleManager;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;
import java.util.Map;

/**
 * @author kelli
 */
public class UserRoleRemoveFromCache extends Task
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
			String	roleId = (String) incomingRequest.get("UserRole_roleId");
			
			if (!Utility.isEmpty(roleId))
			{
				UserRoleManager.getInstance().removeUserRoleFromCache(organizationId, roleId);
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
