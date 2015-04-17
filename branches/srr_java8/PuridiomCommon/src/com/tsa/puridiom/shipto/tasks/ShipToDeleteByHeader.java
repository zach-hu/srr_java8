/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.shipto.tasks;

import java.util.*;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class ShipToDeleteByHeader extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("icheader");
		
        String queryString = "from ShipTo as s where s.id.icHeader = ?" ;

		int retval = dbs.delete(queryString,	
					icHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(retval) ;
					
		return object  ;
	}
}
