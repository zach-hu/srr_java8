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
public class HistoryLogRetrieveByHistoryLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        //String queryString = "from HistoryLog as hst where hst.icHeaderHistory = ? and hst.icLineHistory = ?";\
        String queryString = "from HistoryLog as hst where hst.icLineHistory = ? order by hst.icHistory ASC";

		//String icHeaderHistory = (String)incomingRequest.get("HistoryLog_icHeaderHistory");
		String icLineHistory = (String)incomingRequest.get("HistoryLog_icLineHistory");
		if (icLineHistory == null || icLineHistory.trim().length() == 0) icLineHistory = "0" ;

		//BigDecimal bdHeaderHistory = new BigDecimal(icHeaderHistory);
		BigDecimal bdLineHistory = new BigDecimal(icLineHistory);

		List result = dbs.query(queryString,
					//new Object[] {bdHeaderHistory, bdLineHistory},
					//new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;
					new Object[] {bdLineHistory}, new Type[] {Hibernate.BIG_DECIMAL}) ;

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
