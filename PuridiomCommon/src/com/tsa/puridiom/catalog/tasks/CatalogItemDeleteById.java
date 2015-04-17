package com.tsa.puridiom.catalog.tasks;

import com.tsa.puridiom.entity.*;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

public class CatalogItemDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		CatalogItem catalogItem = (CatalogItem)incomingRequest.get("catalogItem");
		if(catalogItem == null)
		{
			catalogItem = new CatalogItem();
		}
		CatalogItemSetValuesPK setValues = new CatalogItemSetValuesPK();
		setValues.setValues(incomingRequest, catalogItem);
		dbs.delete(catalogItem);
		this.setStatus(dbs.getStatus()) ;
		return catalogItem ;
	}

}