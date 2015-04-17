/*
 * Created on Sept 30, 2005 
 */
package com.tsa.puridiom.recentrequisition.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.Hibernate;
/**
 * @author Kelli
 */
public class RecentRequisitionDeleteByIc extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icReqHeader = (String) incomingRequest.get("RecentRequisition_icReqHeader");
		if (!Utility.isEmpty(icReqHeader)) {
			BigDecimal bdReqHeader = new BigDecimal(icReqHeader) ;
			
	        String queryString = "from RecentRequisition as r where r.id.icReqHeader = ?" ;
	
			dbs.delete(queryString, bdReqHeader, Hibernate.BIG_DECIMAL) ;
			
			this.setStatus(dbs.getStatus());
		}

		return object	;
	}
}
