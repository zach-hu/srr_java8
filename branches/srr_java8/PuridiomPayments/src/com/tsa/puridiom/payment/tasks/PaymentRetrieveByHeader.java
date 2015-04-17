/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.payment.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator 
 */
public class PaymentRetrieveByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("Payment_icPoHeader");
		
        String queryString = "from Payment as b where b.id.icPoHeader = ?" ;

		BigDecimal bdHeader = new BigDecimal(icHeader);

		List result = dbs.query(queryString, bdHeader,	Hibernate.BIG_DECIMAL) ;

		this.setStatus(dbs.getStatus()) ;					
		return result  ;
	}
}
