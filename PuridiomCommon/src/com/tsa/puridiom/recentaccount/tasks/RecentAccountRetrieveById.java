package com.tsa.puridiom.recentaccount.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RecentAccountRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String userId = (String) incomingRequest.get("RecentAccount_userId");
			String accountString = (String) incomingRequest.get("RecentAccount_accountString");

			String queryString = "from RecentAccount as RecentAccount where RecentAccount.id.userId = ? and RecentAccount.id.accountString = ? ";
			List resultList = dbs.query(queryString, new Object[] { userId, accountString } , new Type[] { Hibernate.STRING, Hibernate.STRING } );

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
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