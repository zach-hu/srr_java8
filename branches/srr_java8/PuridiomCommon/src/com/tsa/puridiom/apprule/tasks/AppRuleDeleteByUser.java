/*
 * Created on December 23, 2004 
 */
package com.tsa.puridiom.apprule.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class AppRuleDeleteByUser extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String approver = (String) incomingRequest.get("AppRule_userId");
		
        String queryString = "from AppRule as a where a.id.userId = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {approver}, new Type[] {Hibernate.STRING}));
					
		return object;
	}
}
