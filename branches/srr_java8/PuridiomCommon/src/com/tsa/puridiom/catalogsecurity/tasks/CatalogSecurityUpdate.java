package com.tsa.puridiom.catalogsecurity.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

public class CatalogSecurityUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			CatalogSecurity catalogSecurity = (CatalogSecurity)incomingRequest.get("catalogSecurity");
			if (catalogSecurity== null)
			{
				throw new Exception ("CatalogSecurity was not found.");
			}

			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(catalogSecurity);

			result = catalogSecurity;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			e.printStackTrace();
			Log.error(this, "Error update catalogSecurity: \r\n" + e.getMessage());
			throw e;
		}
		return result;
	}
}
