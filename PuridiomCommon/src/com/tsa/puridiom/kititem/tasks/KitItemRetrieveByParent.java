/*
 * Created on January 18, 2007
 */
package com.tsa.puridiom.kititem.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.Map;

/**
 * @author kathleen
 */
public class KitItemRetrieveByParent extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String parentCatalogId = (String ) incomingRequest.get("KitItem_parentCatalogId");
			String parentItemNumber = (String ) incomingRequest.get("KitItem_parentItemNumber");

			String queryString = "from KitItem as KitItem where KitItem.id.parentCatalogId = ? and KitItem.id.parentItemNumber = ? ";
			result = dbs.query(queryString, new Object[] { parentCatalogId, parentItemNumber } , new Type[] { Hibernate.STRING, Hibernate.STRING });

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