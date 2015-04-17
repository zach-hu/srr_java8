/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.property.tasks;

import java.util.*;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class PropertyRetrieveBySection extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String section = (String)incomingRequest.get("Property_section");

        String queryString = "select prop.id.property, prop.value from Property as prop where prop.id.section  = ?";

		List result = dbs.query(queryString,section,Hibernate.STRING) ;
		
		this.setStatus(dbs.getStatus());
		
		return result ;
	}
}
