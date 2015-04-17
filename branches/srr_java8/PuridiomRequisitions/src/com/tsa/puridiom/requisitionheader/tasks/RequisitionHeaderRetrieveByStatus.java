/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisitionheader.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import org.hibernate.*;
import java.util.List;
import java.util.Map;

/**
 * @author Kelli
 */
public class RequisitionHeaderRetrieveByStatus extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String reqStatus = (String)incomingRequest.get("RequisitionHeader_status");
		
        String queryString = "from RequisitionHeader as b where b.status = ?" ;

		List result = dbs.query(queryString, reqStatus,	Hibernate.STRING) ;
					
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
	
}
