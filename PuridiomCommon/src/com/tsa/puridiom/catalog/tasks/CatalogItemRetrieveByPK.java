/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.catalog.tasks;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator 
 */
public class CatalogItemRetrieveByPK extends Task 
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String catalogId = (String)incomingRequest.get("CatalogItem_id_catalogId");
			String itemNumber = (String)incomingRequest.get("CatalogItem_id_itemNumber");
			
	        String queryString = "from CatalogItem as catItem where catItem.id.catalogId  = ? and catItem.id.itemNumber = ?";
	
			List resultList = dbs.query(queryString, new Object[] {catalogId, itemNumber}, new Type[] {Hibernate.STRING, Hibernate.STRING}) ;
			
			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
		
	}
}
