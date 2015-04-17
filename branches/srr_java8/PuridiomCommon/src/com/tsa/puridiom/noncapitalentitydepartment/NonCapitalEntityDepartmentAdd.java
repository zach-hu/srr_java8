package com.tsa.puridiom.noncapitalentitydepartment;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class NonCapitalEntityDepartmentAdd extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			NonCapitalEntityDepartment nonCapitalEntityDepartment = (NonCapitalEntityDepartment)incomingRequest.get("nonCapitalEntityDepartment");
			if (nonCapitalEntityDepartment == null)
			{
				throw new Exception ("NonCapitalEntityDepartment was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(nonCapitalEntityDepartment);

			result = nonCapitalEntityDepartment;
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