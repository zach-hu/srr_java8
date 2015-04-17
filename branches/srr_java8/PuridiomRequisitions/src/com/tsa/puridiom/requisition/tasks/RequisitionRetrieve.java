/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.requisition.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator 
 */
public class RequisitionRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		BigDecimal bdReqHeader = (BigDecimal) incomingRequest.get("RequisitionHeader_icReqHeader") ;
		
        String queryString = "from RequisitionHeader as b where b.icReqHeader = ?" ;

		List result = dbs.query(queryString,	bdReqHeader, Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(dbs.getStatus()) ;
					
		return result ;
	}
	
}
