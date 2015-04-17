package com.tsa.puridiom.catalog.tasks;

import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class CatalogItemRetrieveByCatalog extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from CatalogItem as catalogitem where 1=1 ");
		if(incomingRequest.containsKey("CatalogItem_catalogId"))
		{			
			String catalogId = (String) incomingRequest.get("CatalogItem_catalogId");
			queryString.append(" AND catalogitem.id.catalogId = '" + catalogId + "'");
		}
		
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}