/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.CatalogComponent;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class CatalogComponentRetrieveAll extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		CatalogComponent catalogComponent = new CatalogComponent();
		CatalogComponentValues.setValues(object, catalogComponent);
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		dbs.add(catalogComponent);
		
		this.setStatus(dbs.getStatus()) ;
					
		return catalogComponent;

	}
}
