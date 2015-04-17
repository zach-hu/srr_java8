package com.tsa.puridiom.department.tasks;

import com.tsa.puridiom.entity.Department;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class DepartmentDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Department department = (Department)incomingRequest.get("department");
		if(department == null)
		{
			department = new Department();
		}
		DepartmentSetValuesPK setValues = new DepartmentSetValuesPK();
		setValues.setValues(incomingRequest, department);
		dbs.delete(department);
		this.setStatus(dbs.getStatus()) ;
		return department ;
	}

}