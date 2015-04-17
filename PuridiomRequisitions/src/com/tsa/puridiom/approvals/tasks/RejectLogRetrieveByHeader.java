/*
 * Created on Aug 19, 2003
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Administrator
 */
public class RejectLogRetrieveByHeader extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("ApprovalLog_icHeader");

			BigDecimal bdh = new BigDecimal(icHeader) ;

	        String queryString = "from RejectLog as a where a.id.icHeader = ?" ;

			ret = dbs.query(queryString, bdh, Hibernate.BIG_DECIMAL) ;

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Reject Log List could not be retrieved.", e);
		}
		return ret ;
	}

}
