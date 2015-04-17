package com.tsa.puridiom.catalog.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class CatalogItemRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String catalogId = (String ) incomingRequest.get("CatalogItem_catalogId");
			String itemNumber = (String ) incomingRequest.get("CatalogItem_itemNumber");

			String queryString = "from CatalogItem as CatalogItem where CatalogItem.id.catalogId = ? and CatalogItem.id.itemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {catalogId, itemNumber, } , new Type[] { Hibernate.STRING, Hibernate.STRING}) ;
					
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