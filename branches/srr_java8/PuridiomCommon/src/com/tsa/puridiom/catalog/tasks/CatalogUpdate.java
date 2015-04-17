/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsa.puridiom.entity.Catalog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.*;

/**
 * @author Administrator 
 */
public class CatalogUpdate extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    Catalog catalog = (Catalog) incomingRequest.get("catalog");
			if (catalog == null)
			{
				throw new Exception ("Catalog was not found.");
			}
		
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			dbs.update(catalog);
		
			result = catalog;
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
