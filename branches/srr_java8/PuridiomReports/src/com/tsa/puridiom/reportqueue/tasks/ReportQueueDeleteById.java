package com.tsa.puridiom.reportqueue.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReportQueue;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class ReportQueueDeleteById extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		ReportQueue reportQueue = (ReportQueue)incomingRequest.get("reportQueue");
		if(reportQueue == null)
		{
			reportQueue = new ReportQueue();
		}
		ReportQueueSetValuesPK setValues = new ReportQueueSetValuesPK();
		setValues.setValues(incomingRequest, reportQueue);
		dbs.delete(reportQueue);
		this.setStatus(dbs.getStatus()) ;
		return reportQueue;
	}

}