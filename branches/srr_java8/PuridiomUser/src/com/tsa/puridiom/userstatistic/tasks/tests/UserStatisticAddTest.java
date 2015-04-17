package com.tsa.puridiom.userstatistic.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userstatistic.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class UserStatisticAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			UserStatisticAdd test = new UserStatisticAdd();
			Map incomingRequest = new HashMap();
		
			UserStatistic userStatistic = new UserStatistic();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("userStatistic", userStatistic);
		
			userStatistic = (UserStatistic) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("UserStatisticAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("UserStatisticAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}