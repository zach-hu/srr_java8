package com.tsa.puridiom.organizationpackage.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class OrganizationPackageAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		DBSession dbs = null;
		
		try
		{
			OrganizationPackage organizationPackage = (OrganizationPackage)incomingRequest.get("organizationPackage");
			if (organizationPackage == null)
			{
				throw new Exception ("OrganizationPackage was not found.");
			}
			
			//	Always use host database configuration for this table
			dbs = new DBSession("host") ;
			dbs.startTransaction();
			dbs.add(organizationPackage);
			dbs.commit();

			result = organizationPackage;
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