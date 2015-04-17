package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Report;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;

public class ReportDeleteById extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		Report report = (Report)incomingRequest.get("report");
		if(report == null)
		{
			report = new Report();
		}
		ReportSetValuesPK setValues = new ReportSetValuesPK();
		setValues.setValues(incomingRequest, report);
		dbs.delete(report);
		this.setStatus(dbs.getStatus()) ;
		return report ;
	}

}