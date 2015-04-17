package com.tsa.puridiom.inspectionmte.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionMteRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionMte as inspectionmte where 1=1 ");
		if(incomingRequest.containsKey("InspectionMte_icRecHeader"))
		{			
			String icRecHeader = (String) incomingRequest.get("InspectionMte_icRecHeader");
			queryString.append(" AND inspectionmte.id.icRecHeader = '" + icRecHeader + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_icRecLine"))
		{			
			String icRecLine = (String) incomingRequest.get("InspectionMte_icRecLine");
			queryString.append(" AND inspectionmte.id.icRecLine = '" + icRecLine + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_keySequence"))
		{			
			String keySequence = (String) incomingRequest.get("InspectionMte_keySequence");
			queryString.append(" AND inspectionmte.id.keySequence = '" + keySequence + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_icMsrLine"))
		{			
			String icMsrLine = (String) incomingRequest.get("InspectionMte_icMsrLine");
			queryString.append(" AND inspectionmte.icMsrLine = '" + icMsrLine + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_dateUsed"))
		{			
			String dateUsed = (String) incomingRequest.get("InspectionMte_dateUsed");
			queryString.append(" AND inspectionmte.dateUsed = '" + dateUsed + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_description"))
		{			
			String description = (String) incomingRequest.get("InspectionMte_description");
			queryString.append(" AND inspectionmte.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_lastChange"))
		{			
			String lastChange = (String) incomingRequest.get("InspectionMte_lastChange");
			queryString.append(" AND inspectionmte.lastChange = '" + lastChange + "'");
		}
		if(incomingRequest.containsKey("InspectionMte_lastChangeBy"))
		{			
			String lastChangeBy = (String) incomingRequest.get("InspectionMte_lastChangeBy");
			queryString.append(" AND inspectionmte.lastChangeBy = '" + lastChangeBy + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}