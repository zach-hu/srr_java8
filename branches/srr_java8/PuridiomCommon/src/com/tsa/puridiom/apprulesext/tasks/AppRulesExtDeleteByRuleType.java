/*
 * Created on August 19, 2008
 */
package com.tsa.puridiom.apprulesext.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class AppRulesExtDeleteByRuleType extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
		String ruleType = (String) incomingRequest.get("AppRulesExt_ruleType");

        String queryString = "from AppRulesExt as a where a.id.ruleType = ?";

		this.setStatus(dbs.delete(queryString,	new Object[] {ruleType}, new Type[] {Hibernate.STRING}));

		return object;
	}
}
