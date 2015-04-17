/*
 * Created on Nov 28, 2007
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class UserManagerRemoveUserSession extends Task
{
	/* (non-Javadoc)
	 * @see com.tsa.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
            String sessionId = (String) incomingRequest.get("sessionId");

			if (!HiltonUtility.isEmpty(organizationId) && !HiltonUtility.isEmpty(sessionId))
			{
				UserManager.getInstance().removeUserSession(organizationId, sessionId);
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
			return null;
		}
	}

}
