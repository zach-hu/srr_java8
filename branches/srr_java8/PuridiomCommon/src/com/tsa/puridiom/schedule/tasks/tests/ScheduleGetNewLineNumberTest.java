package com.tsa.puridiom.schedule.tasks.tests;

import com.tsa.puridiom.schedule.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class ScheduleGetNewLineNumberTest
{
	public static void  main (String[] args)
	{
		try
		{
			ScheduleGetNewLineNumber getLineNumber = new ScheduleGetNewLineNumber();
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("dbsession", new DBSession("PURIDIOM"));
			incomingRequest.put("Schedule_scheduleType","R") ;
			incomingRequest.put("Schedule_documentType","RQH") ;
			incomingRequest.put("Schedule_icHeader","3114902500084") ;
			
			Object obj = getLineNumber.executeTask(incomingRequest);
			
			System.out.println("Object: " + obj.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}