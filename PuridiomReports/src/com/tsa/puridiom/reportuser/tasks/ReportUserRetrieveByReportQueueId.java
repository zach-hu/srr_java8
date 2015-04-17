package com.tsa.puridiom.reportuser.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.entity.*;

public class ReportUserRetrieveByReportQueueId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			String sIcReportQueue = (String) incomingRequest.get("ReportUser_icReportQueue");
			ReportQueue reportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			String userId = (String) incomingRequest.get("userId");
			String userQuery = "and ( ReportUser.userId = '"+ userId +"' or ReportUser.publicView='Y' )";

			if (Utility.isEmpty(sIcReportQueue)) {
				sIcReportQueue = (String) incomingRequest.get("ReportQueue_icReportQueue");
			}
			BigDecimal icReportQueue = new BigDecimal(sIcReportQueue);

			if (reportQueue.getOwner().equalsIgnoreCase(userId)) {
				userQuery = "";
			}
			/***********************************************CAMBIO***************************************************/
			String queryString = "from ReportUser as ReportUser where ReportUser.icReportQueue = ? "+userQuery;
			List resultList = dbs.query(queryString, new Object[] {icReportQueue} , new Type[] { Hibernate.BIG_DECIMAL}) ;
			result = resultList;

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