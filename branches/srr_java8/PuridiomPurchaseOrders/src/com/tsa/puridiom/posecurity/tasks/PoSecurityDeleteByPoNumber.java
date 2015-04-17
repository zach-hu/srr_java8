/*
 * Created on February 01, 2005
 */
package com.tsa.puridiom.posecurity.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class PoSecurityDeleteByPoNumber extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String poNumber = (String) incomingRequest.get("PoSecurity_poNumber");
		
        String queryString = "from PoSecurity as ps where ps.id.poNumber = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {poNumber}, new Type[] {Hibernate.STRING}));
					
		return object;
	}
}
