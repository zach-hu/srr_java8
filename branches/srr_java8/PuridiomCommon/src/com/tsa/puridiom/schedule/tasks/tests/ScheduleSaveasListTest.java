package com.tsa.puridiom.schedule.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.schedule.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.math.BigDecimal;
import java.util.*;

public class ScheduleSaveasListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ScheduleSaveasList test = new ScheduleSaveasList();
			Map incomingRequest = new HashMap();
			
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			List scheduleList = new ArrayList();
			
			for (int i=0; i < 3; i++) {
			    Schedule schedule = new Schedule();
			    SchedulePK pk = new SchedulePK();
				pk.setDocumentType("T");
				pk.setIcHeader(new BigDecimal(ukg.getUniqueKey().toString()));
				pk.setLineNumber(new BigDecimal(i));
				pk.setScheduleType("ST");
				schedule.setComp_id(pk);
				schedule.setCompletionDate(Dates.getDate("2004-12-16"));
				schedule.setDescription("Schedule description... TEST " + String.valueOf(i));
				schedule.setRevisedDate(Dates.getDate("2004-12-16"));
				schedule.setScheduleDate(Dates.getDate("2004-12-16"));
				schedule.setStatus("02");
				
				scheduleList.add(schedule);
			}
			
			dbs.startTransaction();
			
			String	newIc = ukg.getUniqueKey().toString();
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("scheduleList", scheduleList);
			incomingRequest.put("newSchedule_icHeader", newIc);
			
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("new ic = " + newIc);
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}