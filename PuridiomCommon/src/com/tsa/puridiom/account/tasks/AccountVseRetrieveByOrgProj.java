package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class AccountVseRetrieveByOrgProj extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String org = (String) incomingRequest.get("AccountVse_org");
		String proj = (String) incomingRequest.get("AccountVse_proj");

//      	String queryString = "from DeltekProjSetup as a where a.orgId = ? and a.projId = ?";
      	String queryString = "from StdTable as a where a.id.tableType = 'AC02' and a.id.tableKey = ?";

		List resultList = dbs.query(queryString, new Object[] {proj}, new Type[] {Hibernate.STRING});

		if (resultList != null && resultList.size() > 0)
		{
			result = resultList.get(0);
		}

		this.setStatus(dbs.getStatus());

		return result;
	}
}