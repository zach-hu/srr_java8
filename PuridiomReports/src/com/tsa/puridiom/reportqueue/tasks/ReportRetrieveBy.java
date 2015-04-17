package com.tsa.puridiom.reportqueue.tasks;

import java.util.List;
import java.util.Map;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
public class ReportRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from Report as report where 1=1 ");
		if(incomingRequest.containsKey("Report_reportTitle"))
		{			
			String reportTitle = (String) incomingRequest.get("Report_reportTitle");
			queryString.append(" AND report.id.reportTitle = '" + reportTitle + "'");
		}
		if(incomingRequest.containsKey("Report_reportDatawindow"))
		{			
			String reportDatawindow = (String) incomingRequest.get("Report_reportDatawindow");
			queryString.append(" AND report.reportDatawindow = '" + reportDatawindow + "'");
		}
		if(incomingRequest.containsKey("Report_reportModule"))
		{			
			String reportModule = (String) incomingRequest.get("Report_reportModule");
			queryString.append(" AND report.id.reportModule = '" + reportModule + "'");
		}
		if(incomingRequest.containsKey("Report_reportDescription"))
		{			
			String reportDescription = (String) incomingRequest.get("Report_reportDescription");
			queryString.append(" AND report.reportDescription = '" + reportDescription + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}