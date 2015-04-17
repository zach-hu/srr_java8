package com.tsa.puridiom.po.approvals.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ApprovalLogRetrieveByPendingApproval extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("ApprovalLog_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );


			String queryString = "from ApprovalLog as ApprovalLog where ApprovalLog.id.icHeader = ? and ApprovalLog.approved = 'A' ";
			List resultList = dbs.query(queryString, new Object[] {icHeader } , new Type[] { Hibernate.BIG_DECIMAL}) ;

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList.get(0);
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}