package com.tsa.puridiom.department.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class DepartmentRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String departmentCode = (String ) incomingRequest.get("Department_departmentCode");

			String queryString = "from Department as Department where Department.departmentCode = ? ";
			List resultList = dbs.query(queryString, new Object[] {departmentCode, } , new Type[] { Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}