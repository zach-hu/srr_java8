/**
 * 
 */
package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.util.List;
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
public class RequisitionLineListRetrieveWithoutAccounts extends Task
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{

		Map incomingRequest = (Map) object;
		List result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			String icHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			BigDecimal bdHeader = new BigDecimal(icHeader);

			String queryString = "from RequisitionLine as rl where rl.icReqHeader = ? and " + " (select count(*) from Account a where a.id.icHeader = ? and a.id.icLine = rl.icReqLine) = 0 "
					+ " order by rl.lineNumber ASC";

			result = dbs.query(queryString, new Object[] { bdHeader, bdHeader }, new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL });

			this.setStatus(dbs.getStatus());
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "RequisitionLineListRetrieveWithoutAccounts failed: " + e.getMessage());

			throw e;
		}

		return result;
	}
}
