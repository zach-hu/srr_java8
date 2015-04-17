/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.CatalogPriceBrk;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class CatalogPriceBrkAdd extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		CatalogPriceBrk catalogPrcBrk = new CatalogPriceBrk();
		CatalogPriceBrkValues.setValues(object, catalogPrcBrk);
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		dbs.add(catalogPrcBrk);
		
		this.setStatus(dbs.getStatus()) ;
					
		return catalogPrcBrk;

	}
}
