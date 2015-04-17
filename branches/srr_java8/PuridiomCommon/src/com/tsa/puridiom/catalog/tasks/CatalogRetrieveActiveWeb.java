package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsa.puridiom.entity.Property;
import java.util.*;

public class CatalogRetrieveActiveWeb extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession");
		String queryString = "from Property as property where property.id.property like 'OPENKAPOW_WEBSITE_%' order by property.id.property";
		List query = dbs.query(queryString);

		List result = new ArrayList();
		for(int i=0; i<query.size(); i++)
		{
			Property register = (Property)query.get(i);
			result.add(register.getValue());
		}

		this.setStatus(dbs.getStatus());
		return result;
	}
}