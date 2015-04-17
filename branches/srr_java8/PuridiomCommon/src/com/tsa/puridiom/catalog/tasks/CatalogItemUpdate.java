package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class CatalogItemUpdate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			CatalogItem catalogItem = (CatalogItem)incomingRequest.get("catalogItem");
			if (catalogItem == null)
			{
				throw new Exception ("CatalogItem was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(catalogItem);
		
			result = catalogItem;
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