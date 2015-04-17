/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Kelli
 */
public class LabelsRetrieveByLanguage extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

		String language = (String) incomingRequest.get("Labels_language");

        String queryString = "from Labels as labels where labels.language = ? order by labels.labelCode ASC";

		List result = dbs.query(queryString, new Object[] {language}, new Type[] {Hibernate.STRING});

		this.setStatus(dbs.getStatus());

		return result;
	}
}
