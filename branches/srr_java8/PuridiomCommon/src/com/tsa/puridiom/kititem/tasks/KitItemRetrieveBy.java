package com.tsa.puridiom.kititem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class KitItemRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from KitItem as kititem where 1=1 ");
		if(incomingRequest.containsKey("KitItem_parentCatalogId"))
		{			
			String parentCatalogId = (String) incomingRequest.get("KitItem_parentCatalogId");
			queryString.append(" AND kititem.id.parentCatalogId = '" + parentCatalogId + "'");
		}
		if(incomingRequest.containsKey("KitItem_parentItemNumber"))
		{			
			String parentItemNumber = (String) incomingRequest.get("KitItem_parentItemNumber");
			queryString.append(" AND kititem.id.parentItemNumber = '" + parentItemNumber + "'");
		}
		if(incomingRequest.containsKey("KitItem_childCatalogId"))
		{			
			String childCatalogId = (String) incomingRequest.get("KitItem_childCatalogId");
			queryString.append(" AND kititem.id.childCatalogId = '" + childCatalogId + "'");
		}
		if(incomingRequest.containsKey("KitItem_childItemNumber"))
		{			
			String childItemNumber = (String) incomingRequest.get("KitItem_childItemNumber");
			queryString.append(" AND kititem.id.childItemNumber = '" + childItemNumber + "'");
		}
		if(incomingRequest.containsKey("KitItem_childQuantity"))
		{			
			String childQuantity = (String) incomingRequest.get("KitItem_childQuantity");
			queryString.append(" AND kititem.childQuantity = '" + childQuantity + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}