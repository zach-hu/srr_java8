/*
 * Created on Feg 22, 2005
 */
package com.tsa.puridiom.catalog.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

/**
 * @author Kelli
 */
public class CatalogRetrieveInternal extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		int maxRows = 0;
		String maxRowsString = (String)incomingRequest.get("maxRows") ;
		try {
			maxRows = Integer.valueOf(maxRowsString).intValue();
		} catch (Exception e) {
			maxRows = 0;
		}
		
        StringBuffer queryString = new StringBuffer("from Catalog as Catalog where Catalog.externalCatalog <> 'Y'");
        queryString.append(" and Catalog.status = '02' order by Catalog.catalogId");

		List result = dbs.query(queryString.toString()) ;

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}
}
