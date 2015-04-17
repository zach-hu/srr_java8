package com.tsa.puridiom.unitofmeasure.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class UnitOfMeasureRetrieveValidUmCode extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "select unitOfMeasure.umCode from UnitOfMeasure as unitOfMeasure" +
				" where unitOfMeasure.status <> '03'";
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}