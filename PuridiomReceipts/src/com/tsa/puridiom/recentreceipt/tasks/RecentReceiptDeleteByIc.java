/*
 * Created on Sept 30, 2005 
 */
package com.tsa.puridiom.recentreceipt.tasks;

import com.tsagate.foundation.utility.Utility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.*;
import org.hibernate.Hibernate;
/**
 * @author Kelli
 */
public class RecentReceiptDeleteByIc extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;
		DBSession dbs = (DBSession) incomingRequest.get("dbsession") ;

		String icRecHeader = (String) incomingRequest.get("RecentReceipt_icRecHeader");
		if (!Utility.isEmpty(icRecHeader)) {
			BigDecimal bdRecHeader = new BigDecimal(icRecHeader) ;
			
	        String queryString = "from RecentReceipt as r where r.id.icRecHeader = ?" ;
	
			dbs.delete(queryString, bdRecHeader, Hibernate.BIG_DECIMAL) ;
			
			this.setStatus(dbs.getStatus());
		}

		return object	;
	}
}
