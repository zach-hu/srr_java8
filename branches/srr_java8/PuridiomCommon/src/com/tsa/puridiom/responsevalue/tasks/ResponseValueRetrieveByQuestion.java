package com.tsa.puridiom.responsevalue.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ResponseValueRetrieveByQuestion extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		
		String icQuestion = (String) incomingRequest.get("ResponseValue_icQuestion");
		String query = "from ResponseValue as responsevalue where responsevalue.id.icQuestion = '" + icQuestion + "'";

		List result = dbs.query(query) ;
		
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}