/*
 * Created on Jan 10, 2006
 */
package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

import com.tsa.puridiom.entity.ApprovalLog;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Kelli
 */
public class ApprovalLogRetrieveCurrentByHeader extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
	        DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeader = (String)incomingRequest.get("ApprovalLog_icHeader");
			String organizationId = (String)incomingRequest.get("organizationId");

			BigDecimal bdh = new BigDecimal(icHeader) ;

	        String queryString = "from ApprovalLog as a where a.id.icHeader = ? and a.approved = 'A'" ;

			List list = dbs.query(queryString, bdh, Hibernate.BIG_DECIMAL) ;
			
			if (list != null && list.size() > 0) {
		        ret = (ApprovalLog) list.get(0);
			}

			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Current Approval Log record could not be retrieved.", e);
		}
		return ret ;
	}
}