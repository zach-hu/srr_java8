/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.payment.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import org.hibernate.*;

/**
 * @author Administrator 
 */
public class PaymentDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		String icHeader = (String)incomingRequest.get("Payment_icPoHeader");
		BigDecimal bdIcHeader = new BigDecimal(icHeader) ;
		
        String queryString = "from Payment as p where p.id.icPoHeader = ?" ;

		int retval = dbs.delete(queryString,	
					bdIcHeader,
					Hibernate.BIG_DECIMAL) ;
					
		this.setStatus(retval) ;
					
		return object  ;
	}
}
