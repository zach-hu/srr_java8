/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Administrator
 */
public class AccountRetrieveByLine extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
	        String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine = ?";

			String icHeader = (String)incomingRequest.get("Account_icHeader");
			String icLine = (String)incomingRequest.get("Account_icLine") ;
			if (icLine == null) {		icLine = "0" ;		}

			BigDecimal bdHeader = new BigDecimal(icHeader);
			BigDecimal bdLine = new BigDecimal(icLine);

			result = dbs.query(queryString, new Object[] {bdHeader, bdLine}, new Type[] {Hibernate.BIG_DECIMAL,Hibernate.BIG_DECIMAL}) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			this.setStatus(Status.FAILED);
			throw new TsaException("Accounts could not be retrieved! ", e);
		}

		return result ;
	}

}
