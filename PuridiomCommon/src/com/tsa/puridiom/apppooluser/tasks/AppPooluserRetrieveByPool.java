package com.tsa.puridiom.apppooluser.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class AppPooluserRetrieveByPool extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String poolid = (String ) incomingRequest.get("AppPooluser_poolid");
			if (!Utility.isEmpty(poolid))
			{
				if (poolid.contains(";"))
				{
					poolid = poolid.replaceFirst(";", "");
				}
			}

	        String queryString = "select a from AppPooluser as a, UserProfile as u where a.id.poolid = ? and a.id.userId = u.userId order by u.approvalAmount ASC";

			result = dbs.query(queryString,	new Object[] {poolid}, new Type[] {Hibernate.STRING});

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}

}