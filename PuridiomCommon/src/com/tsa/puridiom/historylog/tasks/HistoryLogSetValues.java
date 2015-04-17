package com.tsa.puridiom.historylog.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.HistoryLog;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class HistoryLogSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			HistoryLog historyLog = (HistoryLog) incomingRequest.get("historyLog");
			if (historyLog == null)
			{
				historyLog = new HistoryLog();
			}

			if (incomingRequest.containsKey("HistoryLog_icHistory"))
			{
				String icHistoryString = (String) incomingRequest.get("HistoryLog_icHistory");
				if (Utility.isEmpty(icHistoryString))
				{
					icHistoryString = "0";
				}
				BigDecimal icHistory = new BigDecimal ( icHistoryString );
				historyLog.setIcHistory(icHistory);
			}
			if (incomingRequest.containsKey("HistoryLog_icHeaderHistory"))
			{
				String icHeaderHistoryString = (String) incomingRequest.get("HistoryLog_icHeaderHistory");
				if (Utility.isEmpty(icHeaderHistoryString))
				{
					icHeaderHistoryString = "0";
				}
				BigDecimal icHeaderHistory = new BigDecimal ( icHeaderHistoryString );
				historyLog.setIcHeaderHistory(icHeaderHistory);
			}
			if (incomingRequest.containsKey("HistoryLog_icLineHistory"))
			{
				String icLineHistoryString = (String) incomingRequest.get("HistoryLog_icLineHistory");
				if (Utility.isEmpty(icLineHistoryString))
				{
					icLineHistoryString = "0";
				}
				BigDecimal icLineHistory = new BigDecimal ( icLineHistoryString );
				historyLog.setIcLineHistory(icLineHistory);
			}
			if (incomingRequest.containsKey("HistoryLog_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("HistoryLog_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				historyLog.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("HistoryLog_icLine"))
			{
				String icLineString = (String) incomingRequest.get("HistoryLog_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				historyLog.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("HistoryLog_doctype"))
			{
				String doctype = (String) incomingRequest.get("HistoryLog_doctype");
				historyLog.setDoctype(doctype);
			}
			if (incomingRequest.containsKey("HistoryLog_description"))
			{
				String description = (String) incomingRequest.get("HistoryLog_description");
				historyLog.setDescription(description);
			}
			if (incomingRequest.containsKey("HistoryLog_status"))
			{
				String status = (String) incomingRequest.get("HistoryLog_status");
				historyLog.setStatus(status);
			}
			if (incomingRequest.containsKey("HistoryLog_logDate"))
			{
				String logDate = (String) incomingRequest.get("HistoryLog_logDate");
				historyLog.setLogDate(logDate);
			}
			if (incomingRequest.containsKey("HistoryLog_logTime"))
			{
				String logTime = (String) incomingRequest.get("HistoryLog_logTime");
				historyLog.setLogTime(logTime);
			}
			if (incomingRequest.containsKey("HistoryLog_userid"))
			{
				String userid = (String) incomingRequest.get("HistoryLog_userid");
				historyLog.setUserid(userid);
            }
			if (incomingRequest.containsKey("HistoryLog_timeZone"))
			{
				String timeZone = (String) incomingRequest.get("HistoryLog_timeZone");
				historyLog.setTimeZone(timeZone);
			}

			result = historyLog;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
