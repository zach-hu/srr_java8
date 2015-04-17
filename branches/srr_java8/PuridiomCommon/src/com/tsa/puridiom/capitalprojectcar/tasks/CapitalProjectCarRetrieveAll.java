package com.tsa.puridiom.capitalprojectcar.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

public class CapitalProjectCarRetrieveAll extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from CapitalProjectCar as CapitalProjectCar";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}