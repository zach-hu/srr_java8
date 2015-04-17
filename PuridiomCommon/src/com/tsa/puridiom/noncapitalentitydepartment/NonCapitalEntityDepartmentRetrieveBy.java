package com.tsa.puridiom.noncapitalentitydepartment;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class NonCapitalEntityDepartmentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from NonCapitalEntityDepartment as ncentitydepartment where 1=1 ");

		if(incomingRequest.containsKey("NonCapitalEntityDepartment_icHeader"))
		{
			String icHeader = (String) incomingRequest.get("NonCapitalEntityDepartment_icHeader");
			queryString.append(" AND ncentitydepartment.id.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("NonCapitalEntityDepartment_division"))
		{
			String division = (String) incomingRequest.get("NonCapitalEntityDepartment_division");
			queryString.append(" AND ncentitydepartment.division = '" + division + "'");
		}
		if(incomingRequest.containsKey("NonCapitalEntityDepartment_entity"))
		{
			String entity = (String) incomingRequest.get("NonCapitalEntityDepartment_entity");
			queryString.append(" AND ncentitydepartment.entity = '" + entity + "'");
		}
		if(incomingRequest.containsKey("NonCapitalEntityDepartment_department"))
		{
			String department = (String) incomingRequest.get("NonCapitalEntityDepartment_department");
			queryString.append(" AND ncentitydepartment.department = '" + department + "'");
		}
		if(incomingRequest.containsKey("NonCapitalEntityDepartment_status"))
		{
			String status = (String) incomingRequest.get("NonCapitalEntityDepartment_status");
			queryString.append(" AND ncentitydepartment.nonCapitalEntityDepartment = '" + status + "'");
		}
		if(incomingRequest.containsKey("NonCapitalEntityDepartment_description"))
		{
			String description = (String) incomingRequest.get("NonCapitalEntityDepartment_description");
			queryString.append(" AND ncentitydepartment.description = '" + description + "'");
		}

		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}