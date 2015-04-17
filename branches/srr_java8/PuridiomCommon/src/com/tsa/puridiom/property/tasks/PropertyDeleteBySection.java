/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.property.tasks;

import java.util.*;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class PropertyDeleteBySection extends Task {
	
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String section = (String)incomingRequest.get("section");

		section = HiltonUtility.ckNull(section).toUpperCase();
        String queryString = "from Property as prop where upper(prop.id.section)  = '" + section + "'";

		dbs.delete(queryString) ;
		//system.out.println("Delete section: " + dbs.getStatus()) ;
	
		this.setStatus(dbs.getStatus());
		return object ;
	}

}
