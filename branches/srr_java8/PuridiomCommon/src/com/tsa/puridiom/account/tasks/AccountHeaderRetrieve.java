/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

import org.hibernate.*;

public class AccountHeaderRetrieve extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;
		
		try{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("Account_icHeader");
			
			BigDecimal bdh = new BigDecimal(icHeader) ;
	
	      	String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = 0";
					
			result = dbs.query(queryString, bdh, Hibernate.BIG_DECIMAL) ;
						
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e){
			Log.error(this,e.toString());
			this.setStatus(Status.FAILED);
		}
		return result ;
	}
	
}
