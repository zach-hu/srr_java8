package com.tsa.puridiom.departmentbuyer.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class DepartmentBuyerAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DepartmentBuyer departmentBuyer = (DepartmentBuyer)incomingRequest.get("departmentBuyer");
			if (departmentBuyer == null)
			{
				throw new Exception ("DepartmentBuyer was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(departmentBuyer);
		
			result = departmentBuyer;
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