/**
 * 
 */
package com.tsa.puridiom.account.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class AccountLineRetrieveByHeader extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String icHeader = (String) incomingRequest.get("Account_icHeader");
			String icLine = "0";
			BigDecimal bdHeader = new BigDecimal(icHeader);
			BigDecimal bdLine = new BigDecimal(icLine);

			String queryString = "from Account as a where a.id.icHeader = ? AND a.id.icLine <> ?";

			result = dbs.query(queryString, new Object[] { bdHeader, bdLine }, new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL });

			this.setStatus(dbs.getStatus());

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "Account Lines could not be retrieved! ");

			throw e;
		}

		return result;
	}

}
