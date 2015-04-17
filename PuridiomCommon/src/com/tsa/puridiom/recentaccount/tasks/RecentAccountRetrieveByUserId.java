package com.tsa.puridiom.recentaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import java.util.Map;

public class RecentAccountRetrieveByUserId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String userId = (String) incomingRequest.get("userId");

			String queryString = "from RecentAccount as RecentAccount where RecentAccount.id.userId = ? order by RecentAccount.accountCount DESC";
			result = dbs.query(queryString, userId , Hibernate.STRING );

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