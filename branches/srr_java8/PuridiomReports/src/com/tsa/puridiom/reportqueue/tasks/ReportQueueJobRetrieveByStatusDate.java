package com.tsa.puridiom.reportqueue.tasks;

import java.util.Date;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.ReportQueueFrequency;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReportQueueJobRetrieveByStatusDate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from ReportQueue as ReportQueue where " +
					"(ReportQueue.status = '" + DocumentStatus.SENDQUEUE_NEW + "' AND " +
							"ReportQueue.frequency = '" + ReportQueueFrequency.ONCE  + "') OR " +
					"(ReportQueue.frequency <> '" + ReportQueueFrequency.ONCE  + "' AND " +
					"(ReportQueue.status = '" + DocumentStatus.SENDQUEUE_NEW + "' OR ReportQueue.status = '" + DocumentStatus.SENDQUEUE_PROCESSED + "' ) AND " +
					"ReportQueue.nextRun = ?  AND " +
					"(ReportQueue.startDate <= ? AND " + "ReportQueue.endDate >= ?))" ;
			String status = (String) incomingRequest.get("ReportQueue_status");
			Date ReportQueue_nextRun = (Date) incomingRequest.get("ReportQueue_nextRun");
			Date ReportQueue_startDate = (Date) incomingRequest.get("ReportQueue_startDate");
			Date ReportQueue_endDate = (Date) incomingRequest.get("ReportQueue_endDate");
			String ReportQueue_deliveryTime = (String) incomingRequest.get("ReportQueue_deliveryTime");

			ret = dbs.query(queryString, new Object[] {ReportQueue_nextRun, ReportQueue_startDate, ReportQueue_endDate} , new Type[] { Hibernate.DATE, Hibernate.DATE, Hibernate.DATE}) ;
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ReportQueue list By Status could not be retrieved: " + e.getMessage(), e);
		}

		return ret ;
	}
}