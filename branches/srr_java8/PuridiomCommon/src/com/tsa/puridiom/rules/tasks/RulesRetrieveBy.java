package com.tsa.puridiom.rules.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class RulesRetrieveBy extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			StringBuffer queryString = new StringBuffer("from Rules as rules where 1 = 1 ");
			List argumentsList = new ArrayList();
			List typesList = new ArrayList();

			if (incomingRequest.containsKey("Rules_moduleAccess"))
			{
				queryString.append(" and rules.moduleAccess = ? ");
				argumentsList.add(incomingRequest.get("Rules_moduleAccess"));
				typesList.add(Hibernate.STRING);
			}
			if (incomingRequest.containsKey("Rules_enabled"))
			{
				queryString.append(" and rules.enabled = ? ");
				argumentsList.add(incomingRequest.get("Rules_enabled"));
				typesList.add(Hibernate.STRING);
			}

			Type hiberTypes[] = (Type[])typesList.toArray(new Type[typesList.size()]);

			List resultList = dbs.query(queryString.toString(), argumentsList.toArray(), hiberTypes);
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			}

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
		}
		return result;
	}
}
