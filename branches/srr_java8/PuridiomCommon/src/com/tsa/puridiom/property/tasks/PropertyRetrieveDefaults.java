/*
 * Created on Nov 2, 2003
 */
package com.tsa.puridiom.property.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.*;
import org.hibernate.Hibernate;
/**
 * @author Kelli
 */
public class PropertyRetrieveDefaults extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		String	section = "%DEFAULTS";
		
        DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;
        String queryString = "from Property as prop where prop.id.section like ?";
        
		List result = dbs.query(queryString,section,Hibernate.STRING) ;
		
		//List result = dbs.query(queryString) ;
		this.setStatus(dbs.getStatus()) ;
		
		return result ;
	}
}
