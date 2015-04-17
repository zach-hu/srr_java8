package com.tsa.puridiom.accountqxref.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AccountQxrefRetrieveById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String location = (String) incomingRequest.get("AccountQxref_location");
		String afe = (String) incomingRequest.get("AccountQxref_afe");

      	String queryString = "from AccountQxref as a where a.id.location = ? and a.id.afe = ?";

		List resultList = dbs.query(queryString, new Object[] {location, afe}, new Type[] {Hibernate.STRING, Hibernate.STRING});

		if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus());

		return result;
	}
}