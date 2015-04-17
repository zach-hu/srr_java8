package com.tsa.puridiom.userstatistic.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userstatistic.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class UserStatisticRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			UserStatisticRetrieveById test = new UserStatisticRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			UserStatistic userStatistic = (UserStatistic) test.executeTask(incomingRequest);
		
			System.out.println("UserStatistic: " + userStatistic.toString());
			System.out.println("UserStatisticRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}