package com.tsa.puridiom.reportqueue.tasks;


import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReportQueueSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }

		try
		{
			ReportQueue reportQueue = (ReportQueue) incomingRequest.get("reportQueue");
			if (reportQueue == null)
			{
				reportQueue = new ReportQueue();
			}
			if (incomingRequest.containsKey("ReportQueue_icReportQueue"))
			{
				String s_icReporQueue = (String) incomingRequest.get("ReportQueue_icReportQueue");
				BigDecimal icReportQueue = new BigDecimal(s_icReporQueue);
				reportQueue.setIcReportQueue(icReportQueue);
			}
			if (incomingRequest.containsKey("ReportQueue_module"))
			{
				String module = (String ) incomingRequest.get("ReportQueue_module");
				reportQueue.setModule(module);
			}
			if (incomingRequest.containsKey("ReportQueue_type"))
			{
				String type = (String ) incomingRequest.get("ReportQueue_type");
				reportQueue.setType(type);
			}
			if (incomingRequest.containsKey("ReportQueue_frequency"))
			{
				String frequency = (String ) incomingRequest.get("ReportQueue_frequency");
				reportQueue.setFrequency(frequency);
			}
			if (incomingRequest.containsKey("ReportQueue_startDate"))
			{
				String ReportQueue_startDate = (String) incomingRequest.get("ReportQueue_startDate");
				Date startDate = Dates.getSqlDate(ReportQueue_startDate, userDateFormat);
				reportQueue.setStartDate(startDate);
			}
			if (incomingRequest.containsKey("ReportQueue_endDate"))
			{
				String ReportQueue_endDate = (String) incomingRequest.get("ReportQueue_endDate");
				Date endDate = Dates.getSqlDate(ReportQueue_endDate, userDateFormat);
				reportQueue.setEndDate(endDate);
			}
			if (incomingRequest.containsKey("ReportQueue_status"))
			{
				String ReportQueue_status = (String ) incomingRequest.get("ReportQueue_status");
				reportQueue.setStatus(ReportQueue_status);
			}
			if (incomingRequest.containsKey("ReportQueue_owner"))
			{
				String ReportQueue_owner = (String ) incomingRequest.get("ReportQueue_owner");
				reportQueue.setOwner(ReportQueue_owner);
			}
			if (incomingRequest.containsKey("ReportQueue_sendFrom"))
			{
				String ReportQueue_sendFrom = (String ) incomingRequest.get("ReportQueue_sendFrom");
				reportQueue.setSendFrom(ReportQueue_sendFrom);
			}
			if (incomingRequest.containsKey("ReportQueue_sendTo"))
			{
				String ReportQueue_sendTo = (String ) incomingRequest.get("ReportQueue_sendTo");
				reportQueue.setSendTo(ReportQueue_sendTo);
			}
			if (incomingRequest.containsKey("ReportQueue_dateAdded"))
			{
				String ReportQueue_dateAdded = (String) incomingRequest.get("ReportQueue_dateAdded");
				reportQueue.setDateAdded(ReportQueue_dateAdded);
			}
			if (incomingRequest.containsKey("ReportQueue_timeAdded"))
			{
				String ReportQueue_timeAdded = (String) incomingRequest.get("ReportQueue_timeAdded");
				reportQueue.setTimeAdded(ReportQueue_timeAdded);
			}
			if (incomingRequest.containsKey("ReportQueue_dateSent"))
			{
				String ReportQueue_dateSent = (String ) incomingRequest.get("ReportQueue_dateSent");
				reportQueue.setDateSent(ReportQueue_dateSent);
			}
			if (incomingRequest.containsKey("ReportQueue_timeSent"))
			{
				String ReportQueue_timeSent = (String ) incomingRequest.get("ReportQueue_timeSent");
				reportQueue.setTimeSent(ReportQueue_timeSent);
			}
			if (incomingRequest.containsKey("ReportQueue_deliveryTime"))
			{
				String ReportQueue_deliveryTime = (String) incomingRequest.get("ReportQueue_deliveryTime");
				reportQueue.setDeliveryTime(ReportQueue_deliveryTime);
			}
			if (incomingRequest.containsKey("ReportQueue_deliveryDay"))
			{
				String ReportQueue_deliveryDay = (String ) incomingRequest.get("ReportQueue_deliveryDay");
				reportQueue.setDeliveryDay(ReportQueue_deliveryDay);
			}
			if (incomingRequest.containsKey("ReportQueue_whereClause"))
			{
				String ReportQueue_whereClause = (String ) incomingRequest.get("ReportQueue_whereClause");
				reportQueue.setWhereClause(ReportQueue_whereClause);
			}
			if (incomingRequest.containsKey("ReportQueue_nextRun"))
			{
				String ReportQueue_nextRun = (String) incomingRequest.get("ReportQueue_nextRun");
				Date nextRun = Dates.getSqlDate(ReportQueue_nextRun, userDateFormat);
				reportQueue.setNextRun(nextRun);
			}
			if (incomingRequest.containsKey("ReportQueue_name"))
			{
				String ReportQueue_name = (String) incomingRequest.get("ReportQueue_name");
				reportQueue.setName(ReportQueue_name);
			}
			if (incomingRequest.containsKey("ReportQueue_alias"))
			{
				String ReportQueue_alias = (String) incomingRequest.get("ReportQueue_alias");
				reportQueue.setAlias(ReportQueue_alias);
			}
			if (incomingRequest.containsKey("ReportQueue_publicView"))
			{
				String ReportQueue_publicView = (String) incomingRequest.get("ReportQueue_publicView");
				reportQueue.setPublicView(ReportQueue_publicView);
			}
			if (incomingRequest.containsKey("ReportQueue_attempts"))
			{
				String ReportQueue_attempts = (String) incomingRequest.get("ReportQueue_attempts");
				BigDecimal attempts = new BigDecimal(ReportQueue_attempts);
				reportQueue.setIcReportQueue(attempts);
			}
			result = reportQueue;
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