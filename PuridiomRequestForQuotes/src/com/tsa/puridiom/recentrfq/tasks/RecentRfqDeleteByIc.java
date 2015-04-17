/*
 * Created on Dec 22, 2005 
 */
package com.tsa.puridiom.recentrfq.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.Hibernate;
/**
 * @author Kelli
 */
public class RecentRfqDeleteByIc extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icRfqHeader = (String) incomingRequest.get("RecentRfq_icRfqHeader");
		if (!Utility.isEmpty(icRfqHeader)) {
			BigDecimal bdRfqHeader = new BigDecimal(icRfqHeader) ;
			
	        String queryString = "from RecentRfq as r where r.id.icRfqHeader = ?" ;
	
			dbs.delete(queryString, bdRfqHeader, Hibernate.BIG_DECIMAL) ;
			
			this.setStatus(dbs.getStatus());
		}

		return object	;
	}
}
