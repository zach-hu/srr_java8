package com.tsa.puridiom.invmethod.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class InvMethodRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from InvMethod as invmethod where 1=1 ");
		if(incomingRequest.containsKey("InvMethod_methodId"))
		{			
			String methodId = (String) incomingRequest.get("InvMethod_methodId");
			queryString.append(" AND invmethod.methodId = '" + methodId + "'");
		}
		if(incomingRequest.containsKey("InvMethod_description"))
		{			
			String description = (String) incomingRequest.get("InvMethod_description");
			queryString.append(" AND invmethod.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("InvMethod_notes"))
		{			
			String notes = (String) incomingRequest.get("InvMethod_notes");
			queryString.append(" AND invmethod.notes = '" + notes + "'");
		}
		if(incomingRequest.containsKey("InvMethod_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("InvMethod_dateEntered");
			queryString.append(" AND invmethod.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("InvMethod_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("InvMethod_dateExpires");
			queryString.append(" AND invmethod.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("InvMethod_owner"))
		{			
			String owner = (String) incomingRequest.get("InvMethod_owner");
			queryString.append(" AND invmethod.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("InvMethod_status"))
		{			
			String status = (String) incomingRequest.get("InvMethod_status");
			queryString.append(" AND invmethod.status = '" + status + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}