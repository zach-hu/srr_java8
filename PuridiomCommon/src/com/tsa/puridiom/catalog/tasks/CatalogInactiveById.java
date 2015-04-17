package com.tsa.puridiom.catalog.tasks;

import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;


public class CatalogInactiveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String itemNumber = (String ) incomingRequest.get("as_itemNumber");

			String queryString = "from CatalogItem as CatalogItem where CatalogItem.id.itemNumber = ? and CatalogItem.status = 'On Hold' ";
			List resultList = dbs.query(queryString, new Object[] {itemNumber } , new Type[] { Hibernate.STRING}) ;

			String iteminactive = "0";
			if (resultList != null && resultList.size() > 0)
			{
				iteminactive = "1";
			}
			this.setStatus(dbs.getStatus()) ;
			incomingRequest.put("item_inactive", iteminactive);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}