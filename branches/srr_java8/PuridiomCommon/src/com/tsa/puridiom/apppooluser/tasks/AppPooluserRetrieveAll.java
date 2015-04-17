package com.tsa.puridiom.apppooluser.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

public class AppPooluserRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from AppPooluser as appPooluser";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}