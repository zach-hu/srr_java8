package com.tsa.puridiom.department.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class DepartmentRetrieveValidatePoSecurity extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			result = dbs.query("SELECT Department.departmentCode, Department.departmentName FROM Department as Department " +
					"WHERE Department.status <> '03' ORDER BY Department.departmentCode ASC");
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "DepartmentRetrieveValidateByCode: " + e.getMessage());
		}
		return result;
	}
}