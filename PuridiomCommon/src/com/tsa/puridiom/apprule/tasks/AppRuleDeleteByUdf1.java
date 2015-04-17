/*
 * Created on December 23, 2004 
 */
package com.tsa.puridiom.apprule.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class AppRuleDeleteByUdf1 extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String udf1Code = (String) incomingRequest.get("AppRule_udf1Code");
		
        String queryString = "from AppRule as a where a.id.udf1Code = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {udf1Code}, new Type[] {Hibernate.STRING}));
					
		return object;
	}
}
