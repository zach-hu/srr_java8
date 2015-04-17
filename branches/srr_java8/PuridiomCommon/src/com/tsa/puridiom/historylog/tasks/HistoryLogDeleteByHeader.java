/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;
import org.hibernate.*;

/**
 * @author Administrator 
 */
public class HistoryLogDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		
        String queryString = "from HistoryLog as hst where hst.icHeader = :bdh";
		
		String icHeader = (String)incomingRequest.get("HistoryLog_icHeader");
		BigDecimal bdHeader = new BigDecimal(icHeader);

		this.setStatus(dbs.delete(queryString,bdHeader,Hibernate.BIG_DECIMAL)) ;
		
		return object ;
	}
}
