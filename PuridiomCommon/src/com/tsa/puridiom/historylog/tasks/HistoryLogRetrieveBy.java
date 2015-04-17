package com.tsa.puridiom.historylog.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Task;
import java.util.List;
import java.util.Map;
public class HistoryLogRetrieveBy extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
		StringBuffer queryString = new StringBuffer("from HistoryLog as historylog where 1=1 ");
		if(incomingRequest.containsKey("HistoryLog_icHistory"))
		{			
			String icHistory = (String) incomingRequest.get("HistoryLog_icHistory");
			queryString.append(" AND historylog.id.icHistory = '" + icHistory + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_icHeaderHistory"))
		{			
			String icHeaderHistory = (String) incomingRequest.get("HistoryLog_icHeaderHistory");
			queryString.append(" AND historylog.icHeaderHistory = '" + icHeaderHistory + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_icLineHistory"))
		{			
			String icLineHistory = (String) incomingRequest.get("HistoryLog_icLineHistory");
			queryString.append(" AND historylog.icLineHistory = '" + icLineHistory + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_icHeader"))
		{			
			String icHeader = (String) incomingRequest.get("HistoryLog_icHeader");
			queryString.append(" AND historylog.icHeader = '" + icHeader + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_icLine"))
		{			
			String icLine = (String) incomingRequest.get("HistoryLog_icLine");
			queryString.append(" AND historylog.icLine = '" + icLine + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_doctype"))
		{			
			String doctype = (String) incomingRequest.get("HistoryLog_doctype");
			queryString.append(" AND historylog.doctype = '" + doctype + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_description"))
		{			
			String description = (String) incomingRequest.get("HistoryLog_description");
			queryString.append(" AND historylog.description = '" + description + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_status"))
		{			
			String status = (String) incomingRequest.get("HistoryLog_status");
			queryString.append(" AND historylog.status = '" + status + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_logDate"))
		{			
			String logDate = (String) incomingRequest.get("HistoryLog_logDate");
			queryString.append(" AND historylog.logDate = '" + logDate + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_logTime"))
		{			
			String logTime = (String) incomingRequest.get("HistoryLog_logTime");
			queryString.append(" AND historylog.logTime = '" + logTime + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_userid"))
		{			
			String userid = (String) incomingRequest.get("HistoryLog_userid");
			queryString.append(" AND historylog.userid = '" + userid + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_logMills"))
		{			
			String logMills = (String) incomingRequest.get("HistoryLog_logMills");
			queryString.append(" AND historylog.logMills = '" + logMills + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_ipAddress"))
		{			
			String ipAddress = (String) incomingRequest.get("HistoryLog_ipAddress");
			queryString.append(" AND historylog.ipAddress = '" + ipAddress + "'");
		}
		if(incomingRequest.containsKey("HistoryLog_timeZone"))
		{			
			String timeZone = (String) incomingRequest.get("HistoryLog_timeZone");
			queryString.append(" AND historylog.timeZone = '" + timeZone + "'");
		}
		List result = dbs.query(queryString.toString()) ;
		this.setStatus(dbs.getStatus()) ;
		return result ;
	}
}
