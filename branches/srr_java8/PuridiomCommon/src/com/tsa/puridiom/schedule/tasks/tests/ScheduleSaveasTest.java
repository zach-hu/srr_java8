package com.tsa.puridiom.schedule.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.schedule.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.math.BigDecimal;
import java.util.*;

public class ScheduleSaveasTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ScheduleSaveas test = new ScheduleSaveas();
			Map incomingRequest = new HashMap();
			
			Schedule schedule = new Schedule();
			SchedulePK pk = new SchedulePK();
			pk.setDocumentType("T");
			pk.setIcHeader(new BigDecimal(123456));
			pk.setLineNumber(new BigDecimal("1"));
			pk.setScheduleType("ST");
			schedule.setComp_id(pk);
			schedule.setCompletionDate(Dates.getDate("2004-12-16"));
			schedule.setDescription("Schedule description... TEST");
			schedule.setRevisedDate(Dates.getDate("2004-12-16"));
			schedule.setScheduleDate(Dates.getDate("2004-12-16"));
			schedule.setStatus("02");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("schedule", schedule);
			incomingRequest.put("newSchedule_icHeader", "2123456");
			
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}