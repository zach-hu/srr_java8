/*
 * Created on Sep 26, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.*;


import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo 
 */
public class CatalogRetrieveAll extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String catID = (String)incomingRequest.get("catalogId");
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from Catalog as catalog";
		
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
			
		return result ;
	}

}
