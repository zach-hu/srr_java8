package com.tsa.puridiom.kititem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.List;
import java.util.Map;

public class KitItemRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String parentCatalogId = (String ) incomingRequest.get("KitItem_parentCatalogId");
			String parentItemNumber = (String ) incomingRequest.get("KitItem_parentItemNumber");
			String childCatalogId = (String ) incomingRequest.get("KitItem_childCatalogId");
			String childItemNumber = (String ) incomingRequest.get("KitItem_childItemNumber");

			String queryString = "from KitItem as KitItem where KitItem.id.parentCatalogId = ? and KitItem.id.parentItemNumber = ? and KitItem.id.childCatalogId = ? and KitItem.id.childItemNumber = ? ";
			List resultList = dbs.query(queryString, new Object[] {parentCatalogId, parentItemNumber, childCatalogId, childItemNumber, } , new Type[] { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING}) ;
					
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