package com.tsa.puridiom.checklistresponse.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ChecklistResponseRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ChecklistResponse as checklistresponse where 1=1 ");
		if(incomingRequest.containsKey("ChecklistResponse_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("ChecklistResponse_icHeader");
			queryString.append(" AND checklistresponse.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("ChecklistResponse_icQuestion"))
		{			
			String icQuestion = (String) incomingRequest.get("ChecklistResponse_icQuestion");
			queryString.append(" AND checklistresponse.id.icQuestion = '" + icQuestion + "'");
		}
		if(incomingRequest.containsKey("ChecklistResponse_response"))
		{			
			String response = (String) incomingRequest.get("ChecklistResponse_response");
			queryString.append(" AND checklistresponse.response = '" + response + "'");
		}
		if(incomingRequest.containsKey("ChecklistResponse_textResponse"))
		{			
			String textResponse = (String) incomingRequest.get("ChecklistResponse_textResponse");
			queryString.append(" AND checklistresponse.textResponse = '" + textResponse + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}