package com.tsa.puridiom.organization.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class OrganizationAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		DBSession dbs = null;

		try
		{
		    Organization organization = (Organization)incomingRequest.get("organization");
			if (organization == null)
			{
				throw new Exception ("Organization was not found.");
			}

			//	Always use host database configuration for this table
			dbs = new DBSession("host") ;
			dbs.startTransaction();
			dbs.add(organization);
			dbs.commit();

			result = organization;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
		    if (dbs != null)
		    {
		        dbs.rollback();
		    }
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}