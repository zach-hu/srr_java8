/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;

import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class LabelsDeleteById extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

        String icLabel = (String) incomingRequest.get("Labels_icLabel");

        String queryString = "from Labels as labels where labels.icLabel = ?";

		this.setStatus(dbs.delete(queryString, new Object[] {icLabel}, new Type[] {Hibernate.STRING}));

		return object;
	}
}
