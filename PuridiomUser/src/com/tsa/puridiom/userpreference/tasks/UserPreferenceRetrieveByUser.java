/*
 * Created on January 19, 2005
 */
package com.tsa.puridiom.userpreference.tasks;

import java.util.*;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class UserPreferenceRetrieveByUser extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String userId = (String)incomingRequest.get("UserPreference_userId");
		
        String queryString = "from UserPreference as userPrefs where userPrefs.id.userId  = ?";

        List result = dbs.query(queryString, userId, Hibernate.STRING);
		this.setStatus(dbs.getStatus());
					
		return result;
	}
}
