/*
 * Created on March 15, 2005
 */
package com.tsa.puridiom.catalog.tasks;

import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;
/**
 * @author Kelli
 */
public class CatalogRetrieveActiveForItemSearch extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String queryString = "select catalog.catalogId,  catalog.externalCatalog, catalog.ordersOnly, catalog.title ,catalog.webCatalog  from Catalog as catalog where catalog.status = '02' or (catalog.status = '01' and catalog.dateExpires >= ?) order by catalog.catalogId";

		List result = dbs.query(queryString, new Object[] { Dates.getDate(Dates.today("", (String) incomingRequest.get("userTimeZone"))) }, new Type[] { Hibernate.DATE }) ;

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
