/*
 * Created on August 19, 2008
 */
package com.tsa.puridiom.apprulesext.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class AppRulesExtRetrieveByRuleType extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String ruleType = (String) incomingRequest.get("AppRulesExt_ruleType");

        String queryString = "from AppRulesExt as a where a.id.ruleType = ? order by a.ruleOrder ASC";

		List result = dbs.query(queryString,	new Object[] {ruleType}, new Type[] {Hibernate.STRING});

		this.setStatus(dbs.getStatus());

		return result;
	}
}
