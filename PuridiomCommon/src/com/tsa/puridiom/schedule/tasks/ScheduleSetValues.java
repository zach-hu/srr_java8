package com.tsa.puridiom.schedule.tasks;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ScheduleSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		String organizationId = (String)incomingRequest.get("organizationId");
		String userDateFormat = (String) incomingRequest.get("userDateFormat");

        if (Utility.isEmpty(userDateFormat)) 
        {
            userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
        }
        
		try
		{
			SchedulePK comp_id = new SchedulePK();
			Schedule schedule = (Schedule) incomingRequest.get("schedule");
			if (schedule == null)
			{
				schedule = new Schedule();
			}

			if (incomingRequest.containsKey("Schedule_scheduleType"))
			{
				String scheduleType = (String ) incomingRequest.get("Schedule_scheduleType");
				comp_id.setScheduleType(scheduleType);
			}
			if (incomingRequest.containsKey("Schedule_documentType"))
			{
				String documentType = (String ) incomingRequest.get("Schedule_documentType");
				comp_id.setDocumentType(documentType);
			}
			if (incomingRequest.containsKey("Schedule_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("Schedule_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("Schedule_lineNumber"))
			{
				String lineNumberString = (String) incomingRequest.get("Schedule_lineNumber");
				if (Utility.isEmpty(lineNumberString))
				{
					lineNumberString = "0";
				}
				BigDecimal lineNumber = new BigDecimal ( lineNumberString );
				comp_id.setLineNumber(lineNumber);
			}
			if (incomingRequest.containsKey("Schedule_description"))
			{
				String description = (String ) incomingRequest.get("Schedule_description");
				schedule.setDescription(description);
			}
			if (incomingRequest.containsKey("Schedule_scheduleDate"))
			{
				Object scheduleDateObject = incomingRequest.get("Schedule_scheduleDate");
				if (scheduleDateObject instanceof String) 
				{
					String scheduleDateString = (String) incomingRequest.get("Schedule_scheduleDate");
					Date scheduleDate = Dates.getSqlDate(scheduleDateString, userDateFormat);
					schedule.setScheduleDate(scheduleDate);
				} 
				else if (scheduleDateObject instanceof Date) 
				{
					schedule.setScheduleDate((Date) scheduleDateObject);
				}
			}
			if (incomingRequest.containsKey("Schedule_completionDate"))
			{
				String completionDateString = (String) incomingRequest.get("Schedule_completionDate");
				if (!Utility.isEmpty(completionDateString))
				{
					Date completionDate = Dates.getSqlDate(completionDateString, userDateFormat);
					schedule.setCompletionDate(completionDate);
				}
			}
			if (incomingRequest.containsKey("Schedule_status"))
			{
				String status = (String ) incomingRequest.get("Schedule_status");
				schedule.setStatus(status);
			}
			if (incomingRequest.containsKey("Schedule_revisedDate"))
			{
				String revisedDateString = (String) incomingRequest.get("Schedule_revisedDate");
				if (!Utility.isEmpty(revisedDateString))
				{
					Date revisedDate = Dates.getSqlDate(revisedDateString, userDateFormat);
					schedule.setRevisedDate(revisedDate);
				}
			}
			if (incomingRequest.containsKey("Schedule_scheduleAmount"))
			{
				String scheduleAmountString = (String) incomingRequest.get("Schedule_scheduleAmount");
				if(Utility.isEmpty(scheduleAmountString))
				{
					scheduleAmountString = "0";
				}
				BigDecimal scheduleAmount = new BigDecimal(scheduleAmountString);
				schedule.setScheduleAmount(scheduleAmount);
			}
			
			schedule.setComp_id(comp_id);

			result = schedule;
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