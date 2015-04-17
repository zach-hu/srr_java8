/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.billto.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator
 */
public class BillToRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("icheader");

        String queryString = "select b.id.icHeader,b.id.icLine, b.id.billToCode, b.quantity, b.attention from " +
        		"BillTo as b where b.id.icHeader = ?" ;

		List result = dbs.query(queryString,	icHeader, Hibernate.BIG_DECIMAL) ;

		this.setStatus(dbs.getStatus()) ;

		return result ;
	}

}
