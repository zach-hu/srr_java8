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
public class CatalogComponentDeleteAll extends Task 
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
		
		String catalogId = (String) incomingRequest.get("catalogId");
		String itemNumber = (String) incomingRequest.get("itemNumber");
		String sequence = (String) incomingRequest.get("sequence");
		StringBuffer where = new StringBuffer("from CatalogComponent as catComponent where ");
		if(catalogId != null)
		{
			where.append("catComponent.id.catalogId = '" + catalogId + "'" );
			if(itemNumber != null)
			{
				where.append(" and catComponent.id.itemNumber = '" + itemNumber + "'" );
			}
			if(sequence != null)
			{
				where.append(" and catComponent.id.sequence = '" + sequence + "'");
			}
		}
		
		dbs.delete(where.toString());
		
		this.setStatus(dbs.getStatus()) ;
					
		return object;

	}
}
