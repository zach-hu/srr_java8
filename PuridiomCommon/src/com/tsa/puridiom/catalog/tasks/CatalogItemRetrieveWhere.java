/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class CatalogItemRetrieveWhere extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from CatalogItem as catItem";
		String where = (String)incomingRequest.get("where");
		queryString = queryString + where;

		List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
}
