/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.shipto.tasks;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

/**
 * @author Administrator 
 */
public class ShipToRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("icheader");
		
		String queryString =
			"select s.id.icHeader,s.id.icLine, s.id.shipToCode,s.id.shipToPriority, " +
					"s.quantity, s.attention, s.shipDate, s.codeType  from ShipTo as s where s.id.icHeader = ?";

		List result = dbs.query(queryString,	
					icHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
}
