package com.tsa.puridiom.inspectioncrit.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InspectionCritRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InspectionCrit as inspectioncrit where 1=1 ");
		if(incomingRequest.containsKey("InspectionCrit_inspectCode"))
		{			
			String inspectCode = (String) incomingRequest.get("InspectionCrit_inspectCode");
			queryString.append(" AND inspectioncrit.id.inspectCode = '" + inspectCode + "'");
		}
		if(incomingRequest.containsKey("InspectionCrit_critNo"))
		{			
			String critNo = (String) incomingRequest.get("InspectionCrit_critNo");
			queryString.append(" AND inspectioncrit.id.critNo = '" + critNo + "'");
		}
		if(incomingRequest.containsKey("InspectionCrit_description"))
		{			
			String description = (String) incomingRequest.get("InspectionCrit_description");
			queryString.append(" AND inspectioncrit.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InspectionCrit_defaultFlag"))
		{			
			String defaultFlag = (String) incomingRequest.get("InspectionCrit_defaultFlag");
			queryString.append(" AND inspectioncrit.defaultFlag = '" + defaultFlag + "'");
		}
		if(incomingRequest.containsKey("InspectionCrit_status"))
		{			
			String status = (String) incomingRequest.get("InspectionCrit_status");
			queryString.append(" AND inspectioncrit.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("InspectionCrit_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("InspectionCrit_dateEntered");
			queryString.append(" AND inspectioncrit.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InspectionCrit_owner"))
		{			
			String owner = (String) incomingRequest.get("InspectionCrit_owner");
			queryString.append(" AND inspectioncrit.owner = '" + owner + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}