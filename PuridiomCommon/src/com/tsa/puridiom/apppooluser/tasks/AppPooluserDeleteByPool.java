/*
 * Created on January 11, 2005
 */
package com.tsa.puridiom.apppooluser.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class AppPooluserDeleteByPool extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String poolid = (String) incomingRequest.get("AppPooluser_poolid");
		
        String queryString = "from AppPooluser as a where a.id.poolid = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {poolid}, new Type[] {Hibernate.STRING}));
					
		return object;
	}
}
