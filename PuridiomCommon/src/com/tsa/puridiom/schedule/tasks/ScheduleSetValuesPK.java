package com.tsa.puridiom.schedule.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.Schedule;
import com.tsa.puridiom.entity.SchedulePK;

public class ScheduleSetValuesPK
{
	public void setValues(Map incomingRequest, Schedule schedule ) throws Exception
	{
		try
		{
			String scheduleType = (String ) incomingRequest.get("Schedule_scheduleType");
			String documentType = (String ) incomingRequest.get("Schedule_documentType");
			String icHeaderString = (String) incomingRequest.get("Schedule_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String lineNumberString = (String) incomingRequest.get("Schedule_lineNumber");
			BigDecimal lineNumber = new BigDecimal ( lineNumberString );
			SchedulePK comp_id = new SchedulePK();
			comp_id.setDocumentType(documentType);
			comp_id.setIcHeader(icHeader);
			comp_id.setLineNumber(lineNumber);
			comp_id.setScheduleType(scheduleType);
			schedule.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}