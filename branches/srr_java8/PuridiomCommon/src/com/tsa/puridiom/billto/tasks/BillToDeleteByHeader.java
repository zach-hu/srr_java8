/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.billto.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator 
 */
public class BillToDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("BillTo_icHeader");
		BigDecimal bdIcHeader = new BigDecimal(icHeader) ;
		
        String queryString = "from BillTo as b where b.id.icHeader = ?" ;

		int retval = dbs.delete(queryString,	
					bdIcHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(retval) ;
					
		return object  ;
	}
}