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
public class CatalogPriceBrkRetrieveById extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map)object;

		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "from CatalogPriceBrk as catPB where ";
		
		String catalogId = (String) incomingRequest.get("catalogId");
		String itemNumber = (String) incomingRequest.get("itemNumber");
		Integer sequence = (Integer) incomingRequest.get("sequence");
		StringBuffer where = new StringBuffer("from CatalogPriceBrk as catPB where ");
		
		if(catalogId != null)
		{
			where.append("catPB.id.catalogId = '" + catalogId + "'" );
		}
		if(itemNumber != null)
		{
			if (where.length() > 0)
			{
				where.append(" and catPB.id.itemNumber = '" + itemNumber + "'" );	
			}
			else
			{
				where.append(" catPB.id.itemNumber = '" + itemNumber + "'" );
			}
		}
		if(sequence != null)
		{
			if (where.length() > 0)
			{
				where.append(" and catPB.id.sequence = '" + sequence.toString() + "'");
			}
			else
			{
				where.append(" catPB.id.sequence = '" + sequence.toString() + "'");
			}
		}

		List result = dbs.query(where.toString()) ;
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
}
