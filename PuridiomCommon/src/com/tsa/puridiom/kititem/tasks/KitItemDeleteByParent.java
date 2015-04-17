/*
 * Created on January 18, 2007
 */
package com.tsa.puridiom.kititem.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author kathleen
 */
public class KitItemDeleteByParent extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String catalogId = (String) incomingRequest.get("KitItem_parentCatalogId");
		String itemNumber = (String) incomingRequest.get("KitItem_parentItemNumber");

        String queryString = "from KitItem as kititem where kititem.id.parentCatalogId = ? and kititem.id.parentItemNumber = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {catalogId, itemNumber}, new Type[] {Hibernate.STRING, Hibernate.STRING}));

		return object;
	}
}
