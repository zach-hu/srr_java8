/*
 * Created on December 18, 2006
 */
package com.tsa.puridiom.catalogpricebrk.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class CatalogPriceBrkDeleteByItem extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String catalogId = (String) incomingRequest.get("CatalogPriceBrk_catalogId");
		String itemNumber = (String) incomingRequest.get("CatalogPriceBrk_itemNumber");

        String queryString = "from CatalogPriceBrk as c where c.id.catalogId = ? and c.id.itemNumber = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {catalogId, itemNumber}, new Type[] {Hibernate.STRING, Hibernate.STRING}));

		return object;
	}
}
