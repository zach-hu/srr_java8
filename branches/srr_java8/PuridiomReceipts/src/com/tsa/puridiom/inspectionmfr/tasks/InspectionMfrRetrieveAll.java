package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;

public class InspectionMfrRetrieveAll extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from InspectionMfr as inspectionMfr";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}

}