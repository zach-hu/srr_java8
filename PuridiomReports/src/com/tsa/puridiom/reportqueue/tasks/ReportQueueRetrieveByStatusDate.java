package com.tsa.puridiom.reportqueue.tasks;

import java.util.Date;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReportQueueRetrieveByStatusDate extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;

			String queryString = "from ReportQueue as ReportQueue where ReportQueue.status = ? OR " +
					"((ReportQueue.nextRun = ? AND ReportQueue.deliveryTime <= ?) AND " +
					"(ReportQueue.startDate <= ? AND ReportQueue.endDate >= ?))" ;
			String status = (String) incomingRequest.get("ReportQueue_status");
			Date ReportQueue_nextRun = (Date) incomingRequest.get("ReportQueue_nextRun");
			Date ReportQueue_startDate = (Date) incomingRequest.get("ReportQueue_startDate");
			Date ReportQueue_endDate = (Date) incomingRequest.get("ReportQueue_endDate");
			String ReportQueue_deliveryTime = (String) incomingRequest.get("ReportQueue_deliveryTime");

			ret = dbs.query(queryString, new Object[] {status, ReportQueue_nextRun, ReportQueue_deliveryTime, ReportQueue_startDate, ReportQueue_endDate} , new Type[] { Hibernate.STRING, Hibernate.DATE, Hibernate.STRING, Hibernate.DATE, Hibernate.DATE}) ;
			this.setStatus(dbs.getStatus()) ;

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("ReportQueue list By Status could not be retrieved");
		}

		return ret ;
	}
}