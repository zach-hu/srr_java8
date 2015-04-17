package com.tsa.puridiom.schedule.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class ScheduleRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Schedule as schedule where 1=1 ");
		if(incomingRequest.containsKey("Schedule_scheduleType"))
		{			
			String scheduleType = (String) incomingRequest.get("Schedule_scheduleType");
			queryString.append(" AND schedule.id.scheduleType = '" + scheduleType + "'");
		}
		if(incomingRequest.containsKey("Schedule_documentType"))
		{			
			String documentType = (String) incomingRequest.get("Schedule_documentType");
			queryString.append(" AND schedule.id.documentType = '" + documentType + "'");
		}
		if(incomingRequest.containsKey("Schedule_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("Schedule_icHeader");
			queryString.append(" AND schedule.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("Schedule_lineNumber"))
		{			
			String lineNumber = (String) incomingRequest.get("Schedule_lineNumber");
			queryString.append(" AND schedule.id.lineNumber = '" + lineNumber + "'");
		}
		if(incomingRequest.containsKey("Schedule_description"))
		{			
			String description = (String) incomingRequest.get("Schedule_description");
			queryString.append(" AND schedule.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("Schedule_scheduleDate"))
		{			
			String scheduleDate = (String) incomingRequest.get("Schedule_scheduleDate");
			queryString.append(" AND schedule.scheduleDate = '" + scheduleDate + "'");
		}
		if(incomingRequest.containsKey("Schedule_completionDate"))
		{			
			String completionDate = (String) incomingRequest.get("Schedule_completionDate");
			queryString.append(" AND schedule.completionDate = '" + completionDate + "'");
		}
		if(incomingRequest.containsKey("Schedule_status"))
		{			
			String status = (String) incomingRequest.get("Schedule_status");
			queryString.append(" AND schedule.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("Schedule_revisedDate"))
		{			
			String revisedDate = (String) incomingRequest.get("Schedule_revisedDate");
			queryString.append(" AND schedule.revisedDate = '" + revisedDate + "'");
		}
		if(incomingRequest.containsKey("Schedule_scheduleAmount"))
		{			
			String scheduleAmount = (String) incomingRequest.get("Schedule_scheduleAmount");
			queryString.append(" AND schedule.scheduleAmount = '" + scheduleAmount + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}