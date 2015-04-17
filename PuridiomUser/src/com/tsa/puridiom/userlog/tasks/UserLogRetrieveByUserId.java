package com.tsa.puridiom.userlog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class UserLogRetrieveByUserId extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String userId = (String)incomingRequest.get("UserLog_userId");

			String queryString = "from UserLog as UserLog where UserLog.userId = ? " +
				"order by UserLog.dateLog desc, UserLog.timeLog desc";
			List resultList = dbs.query(queryString, new Object[] {userId}, new Type[] {Hibernate.STRING});

			result = resultList;

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
