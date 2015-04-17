package com.tsa.puridiom.userprofile.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class UserProfileRetrieveValidatePoSecurity extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			result = dbs.query("SELECT UserProfile.userId, UserProfile.firstName, UserProfile.lastName FROM UserProfile as UserProfile " +
					"WHERE UserProfile.status <> '03' ORDER BY UserProfile.userId ASC");
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "UserProfileRetrieveValidatePoSecurity: " + e.getMessage());
		}
		return result;
	}
}