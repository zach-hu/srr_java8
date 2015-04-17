package com.tsa.puridiom.userlog.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class UserLogAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UserLog userLog = (UserLog)incomingRequest.get("userLog");
			if (userLog == null)
			{
				throw new Exception ("UserLog was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			dbs.add(userLog);

			result = userLog;
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
