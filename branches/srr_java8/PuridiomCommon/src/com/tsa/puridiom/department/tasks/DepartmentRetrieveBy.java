package com.tsa.puridiom.department.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class DepartmentRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Department as department where 1=1 ");
		if(incomingRequest.containsKey("Department_departmentCode"))
		{			
			String departmentCode = (String) incomingRequest.get("Department_departmentCode");
			queryString.append(" AND department.id.departmentCode = '" + departmentCode + "'");
		}
		if(incomingRequest.containsKey("Department_departmentName"))
		{			
			String departmentName = (String) incomingRequest.get("Department_departmentName");
			queryString.append(" AND department.departmentName = '" + departmentName + "'");
		}
		if(incomingRequest.containsKey("Department_deptManager"))
		{			
			String deptManager = (String) incomingRequest.get("Department_deptManager");
			queryString.append(" AND department.deptManager = '" + deptManager + "'");
		}
		if(incomingRequest.containsKey("Department_division"))
		{			
			String division = (String) incomingRequest.get("Department_division");
			queryString.append(" AND department.division = '" + division + "'");
		}
		if(incomingRequest.containsKey("Department_dateEntered"))
		{			
			String dateEntered = (String) incomingRequest.get("Department_dateEntered");
			queryString.append(" AND department.dateEntered = '" + dateEntered + "'");
		}
		if(incomingRequest.containsKey("Department_dateExpires"))
		{			
			String dateExpires = (String) incomingRequest.get("Department_dateExpires");
			queryString.append(" AND department.dateExpires = '" + dateExpires + "'");
		}
		if(incomingRequest.containsKey("Department_owner"))
		{			
			String owner = (String) incomingRequest.get("Department_owner");
			queryString.append(" AND department.owner = '" + owner + "'");
		}
		if(incomingRequest.containsKey("Department_status"))
		{			
			String status = (String) incomingRequest.get("Department_status");
			queryString.append(" AND department.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("Department_deptApprv1"))
		{			
			String deptApprv1 = (String) incomingRequest.get("Department_deptApprv1");
			queryString.append(" AND department.deptApprv1 = '" + deptApprv1 + "'");
		}
		if(incomingRequest.containsKey("Department_deptApprv2"))
		{			
			String deptApprv2 = (String) incomingRequest.get("Department_deptApprv2");
			queryString.append(" AND department.deptApprv2 = '" + deptApprv2 + "'");
		}
		if(incomingRequest.containsKey("Department_deptApprv3"))
		{			
			String deptApprv3 = (String) incomingRequest.get("Department_deptApprv3");
			queryString.append(" AND department.deptApprv3 = '" + deptApprv3 + "'");
		}
		if(incomingRequest.containsKey("Department_deptApprv4"))
		{			
			String deptApprv4 = (String) incomingRequest.get("Department_deptApprv4");
			queryString.append(" AND department.deptApprv4 = '" + deptApprv4 + "'");
		}
		if(incomingRequest.containsKey("Department_deptApprv5"))
		{			
			String deptApprv5 = (String) incomingRequest.get("Department_deptApprv5");
			queryString.append(" AND department.deptApprv5 = '" + deptApprv5 + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}