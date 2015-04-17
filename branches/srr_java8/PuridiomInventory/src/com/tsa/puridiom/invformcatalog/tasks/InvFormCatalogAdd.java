package com.tsa.puridiom.invformcatalog.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class InvFormCatalogAdd extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvFormCatalog invFormCatalog = (InvFormCatalog)incomingRequest.get("invFormCatalog");
			if (invFormCatalog == null)
			{
				throw new Exception ("InvFormCatalog was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.add(invFormCatalog);
		
			result = invFormCatalog;
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