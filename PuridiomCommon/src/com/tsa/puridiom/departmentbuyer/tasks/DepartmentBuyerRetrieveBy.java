package com.tsa.puridiom.departmentbuyer.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class DepartmentBuyerRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from DepartmentBuyer as departmentbuyer where 1=1 ");
		if(incomingRequest.containsKey("DepartmentBuyer_departmentCode"))
		{			
			String departmentCode = (String) incomingRequest.get("DepartmentBuyer_departmentCode");
			queryString.append(" AND departmentbuyer.id.departmentCode = '" + departmentCode + "'");
		}
		if(incomingRequest.containsKey("DepartmentBuyer_userId"))
		{			
			String userId = (String) incomingRequest.get("DepartmentBuyer_userId");
			queryString.append(" AND departmentbuyer.id.userId = '" + userId + "'");
		}
		if(incomingRequest.containsKey("DepartmentBuyer_assignAmount"))
		{			
			String assignAmount = (String) incomingRequest.get("DepartmentBuyer_assignAmount");
			queryString.append(" AND departmentbuyer.assignAmount = '" + assignAmount + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}