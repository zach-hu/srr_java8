package com.tsa.puridiom.recentaccount.tasks;

import com.tsa.puridiom.entity.RecentAccount;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RecentAccountUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RecentAccount recentAccount = (RecentAccount) incomingRequest.get("recentAccount");
			if (recentAccount == null)
			{
				throw new Exception ("RecentAccount was not found.");
			}

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			dbs.update(recentAccount);

			result = recentAccount;
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