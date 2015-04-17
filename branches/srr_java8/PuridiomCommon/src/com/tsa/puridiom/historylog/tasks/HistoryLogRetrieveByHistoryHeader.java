/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.historylog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;

import org.hibernate.*;
import org.hibernate.type.Type;
/**
 * @author Administrator
 */
public class HistoryLogRetrieveByHistoryHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

        String queryString = "from HistoryLog as hst where hst.icHeaderHistory = ? and (hst.doctype like '%H' or hst.doctype = 'REQ') order by hst.icHistory ASC";

		String icHeaderHistory = (String)incomingRequest.get("HistoryLog_icHeaderHistory");
		BigDecimal bdHeaderHistory = new BigDecimal(icHeaderHistory);

		List result = dbs.query(queryString, new Object[] {bdHeaderHistory} , new Type[] {Hibernate.BIG_DECIMAL}) ;

		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
