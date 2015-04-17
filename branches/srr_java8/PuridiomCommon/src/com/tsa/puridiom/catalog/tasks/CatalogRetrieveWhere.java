/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.*;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

/**
 * @author Administrator 
 */
public class CatalogRetrieveWhere extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String catalogId = (String)incomingRequest.get("catalogId");
		
        StringBuffer queryString = new StringBuffer("from Catalog as cat ");
       
        String where = (String)incomingRequest.get("where");
        if(!Utility.isEmpty(where))
        {
        	queryString.append(where);
        }
		
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
}
