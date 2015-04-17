/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.rfq.tasks;

import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

/**
 * @author Kelli
*/
public class RfqValidateNumber extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	rfqNumber = (String) incomingRequest.get("RfqHeader_rfqNumber");
			
			String queryString = "select RfqHeader.rfqNumber from RfqHeader as RfqHeader where RfqHeader.rfqNumber = ?";
			List resultList = dbs.query(queryString, new Object[] {rfqNumber} , new Type[] { Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				throw new Exception("Duplicate Rfq Number Requested!");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}

}
