/*
 * Created on June 120, 2004
 */
package com.tsa.puridiom.usermanager.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**
 * @author kelli
 */
public class UserManagerResetSessionLoginAttempts extends Task
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
			String	sessionId = (String) incomingRequest.get("puridiomSessionId");

			if (!HiltonUtility.isEmpty(sessionId))
			{
				UserManager.getInstance().resetFailedLoginAttempts(sessionId);
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
