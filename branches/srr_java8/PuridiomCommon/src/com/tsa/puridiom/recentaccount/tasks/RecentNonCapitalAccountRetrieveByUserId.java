package com.tsa.puridiom.recentaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

/**  Tasks RecentNonCapitalAccountRetrieveByUserId - Retrieve recent non capital account by user id */

public class RecentNonCapitalAccountRetrieveByUserId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String userId = (String) incomingRequest.get("userId");

			String queryString = "from RecentAccount as RecentAccount " +
								 "where RecentAccount.id.userId = '" + userId + "' " +
								 "and RecentAccount.id.accountString like '%-000000-%' " +
								 "order by RecentAccount.accountCount DESC";

			 result = dbs.query(queryString.toString()) ;

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