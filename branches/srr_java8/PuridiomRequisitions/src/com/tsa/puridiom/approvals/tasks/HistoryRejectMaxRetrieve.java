package com.tsa.puridiom.approvals.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;

public class HistoryRejectMaxRetrieve extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object res = null;
		try
		{
			String icHeader = (String)incomingRequest.get("ApprovalLog_icHeader");
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			BigDecimal bd_icHeader = new BigDecimal(icHeader) ;
	        String queryString = "select max(hisRej.comp_id.icLine) from HistoryReject as hisRej where hisRej.comp_id.icHeader = ? " ;
			List result = dbs.query(queryString, bd_icHeader, Hibernate.BIG_DECIMAL) ;
			if( result != null && !result.isEmpty())
			{
				BigDecimal maxHistoryReject = (BigDecimal) result.get(0);
				res = maxHistoryReject;
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return res;
	}
}