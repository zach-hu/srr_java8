/*
 * Created on Nov 20, 2003
 */
package com.tsa.puridiom.disbursement.tasks;

import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.util.*;

/**
 * @author Kelli
*/
public class DisbursementValidateNumber extends Task {

	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception 
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try 
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String	disbNumber = (String) incomingRequest.get("DisbHeader_disbNumber");
			
			String queryString = "select disbHeader.status from DisbHeader as disbHeader where disbHeader.disbNumber = ?";
			List resultList = dbs.query(queryString, new Object[] {disbNumber} , new Type[] { Hibernate.STRING}) ;
					
			if (resultList != null && resultList.size() > 0)
			{
				throw new Exception("Duplicate Disb Number Requested!");
			}
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e) 
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		finally
		{
			return result ;
		}
	}

}
