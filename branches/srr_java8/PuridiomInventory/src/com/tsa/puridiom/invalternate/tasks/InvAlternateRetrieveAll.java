package com.tsa.puridiom.invalternate.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class InvAlternateRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from InvAlternate as invAlternate";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}