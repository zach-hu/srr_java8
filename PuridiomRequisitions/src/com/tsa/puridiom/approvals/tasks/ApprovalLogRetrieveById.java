package com.tsa.puridiom.approvals.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ApprovalLogRetrieveById extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String icHeaderString = (String) incomingRequest.get("ApprovalLog_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("ApprovalLog_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String sequenceString = (String) incomingRequest.get("ApprovalLog_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );
			String userId = (String ) incomingRequest.get("ApprovalLog_userId");

			String queryString = "from ApprovalLog as ApprovalLog where ApprovalLog.id.icHeader = ? and ApprovalLog.id.icLine = ? and ApprovalLog.id.sequence = ? and ApprovalLog.id.userId = ? ";
			List resultList = dbs.query(queryString, new Object[] {icHeader, icLine, sequence, userId, } , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL, Hibernate.STRING}) ;
					
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