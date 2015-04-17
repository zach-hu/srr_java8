/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.account.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

import java.util.*;
import java.math.*;
import org.hibernate.*;

/**
 * @author Administrator 
 */
public class AccountDeleteByHeader extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		
        String queryString = "from Account as a where a.id.icHeader = ?";
		
		String icHeader = (String)incomingRequest.get("Account_icHeader");
		BigDecimal bdIcHeader = new BigDecimal(icHeader) ;

		this.setStatus(dbs.delete(queryString,bdIcHeader,Hibernate.BIG_DECIMAL)) ;
		
		this.setStatus(dbs.getStatus()) ;
		return object ;
	}
}
