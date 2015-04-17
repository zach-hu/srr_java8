package com.tsa.puridiom.checklistentry.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ChecklistEntryRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from ChecklistEntry as checklistentry where 1=1 ");
		if(incomingRequest.containsKey("ChecklistEntry_icQuestion"))
		{			
			String icQuestion = (String) incomingRequest.get("ChecklistEntry_icQuestion");
			queryString.append(" AND checklistentry.id.icQuestion = '" + icQuestion + "'");
		}
		if(incomingRequest.containsKey("ChecklistEntry_referenceType"))
		{			
			String referenceType = (String) incomingRequest.get("ChecklistEntry_referenceType");
			queryString.append(" AND checklistentry.referenceType = '" + referenceType + "'");
		}
		if(incomingRequest.containsKey("ChecklistEntry_sequence"))
		{			
			String sequence = (String) incomingRequest.get("ChecklistEntry_sequence");
			queryString.append(" AND checklistentry.sequence = '" + sequence + "'");
		}
		if(incomingRequest.containsKey("ChecklistEntry_questionText"))
		{			
			String questionText = (String) incomingRequest.get("ChecklistEntry_questionText");
			queryString.append(" AND checklistentry.questionText = '" + questionText + "'");
		}
		if(incomingRequest.containsKey("ChecklistEntry_responseType"))
		{			
			String responseType = (String) incomingRequest.get("ChecklistEntry_responseType");
			queryString.append(" AND checklistentry.responseType = '" + responseType + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}