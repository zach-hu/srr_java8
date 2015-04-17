/*
 * Created on May 29, 2009
 */
package com.tsa.puridiom.labels.tasks;

import java.util.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class LabelsDeleteByLanguage extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession) incomingRequest.get("dbsession");

        String language = (String) incomingRequest.get("Labels_language");

        if (Utility.isEmpty(language)) {
        	language = "EN";
        }
        String queryString = "from Labels as labels where labels.language = ?";

		this.setStatus(dbs.delete(queryString, new Object[] {language}, new Type[] {Hibernate.STRING}));

		return object;
	}
}
