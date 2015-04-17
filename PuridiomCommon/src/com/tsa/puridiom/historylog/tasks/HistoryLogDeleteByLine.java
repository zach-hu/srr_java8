/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

/**
 * @author Administrator 
 */
public class HistoryLogDeleteByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		
        String queryString = "from HistoryLog as hst where hst.icHeader = ? and hst.icLine = ?";
		
		String icHeader = (String)incomingRequest.get("HistoryLog_icHeader");
		String icLine = (String)incomingRequest.get("HistoryLog_icLine");			
		if (icLine == null || icLine.trim().length() == 0) icLine = "0" ;

		BigDecimal bdHeader = new BigDecimal(icHeader);
		BigDecimal bdLine = new BigDecimal(icLine);

		this.setStatus(dbs.delete(queryString,	
					new Object[] {bdHeader, bdLine},
					new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL})) ;
		
		return object ;
	}
}
