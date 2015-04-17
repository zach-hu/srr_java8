package com.tsa.puridiom.approvals.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ApprovalLogHealRetrieveByHeader extends Task
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

	       	String queryReuseApprover = "from ApprovalLog as a where a.id.icHeader = ?" ;

	        List resultList = dbs.query(queryReuseApprover, new Object[] {bdh } , new Type[] { Hibernate.BIG_DECIMAL}) ;
	        
	        ret = resultList;
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Routing List could not be retrieved.", e);
		}
		return ret ;
	}
}
