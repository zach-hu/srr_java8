/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

import com.tsa.puridiom.entity.*;

/**
 * @author Administrator 
 */
public class CatalogAdd extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		Catalog catalog = new Catalog() ;		

		CatalogSetValues catalogValues = new CatalogSetValues();
		incomingRequest.put("catalog", catalog);
		catalog = (Catalog) catalogValues.executeTask(incomingRequest);
		
		dbs.add(catalog);
		this.setStatus(dbs.getStatus());
		
		return catalog;
	}
	
}
