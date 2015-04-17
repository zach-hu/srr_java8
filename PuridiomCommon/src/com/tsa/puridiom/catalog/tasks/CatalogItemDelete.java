/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class CatalogItemDelete extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		CatalogItem catalogItem = new CatalogItem();
		CatalogItemValues.setValues(object, catalogItem);
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		dbs.delete(catalogItem);
		
		this.setStatus(dbs.getStatus()) ;
					
		return catalogItem;

	}
}
