/*
 * Created on June 15, 2004
 */
package com.tsa.puridiom.invcatalog.tasks;

import java.util.*;


import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author renzo 
 */
public class InvCatalogRetrieveAll extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		//String invCatID = (String)incomingRequest.get("invCatalogID");
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from InvCatalog as invCatalog";
		
		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
			
		return result ;
	}

}
