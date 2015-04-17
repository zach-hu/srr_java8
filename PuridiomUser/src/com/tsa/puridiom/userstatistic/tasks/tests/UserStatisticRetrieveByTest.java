package com.tsa.puridiom.userstatistic.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.userstatistic.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class UserStatisticRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			UserStatisticRetrieveBy test = new UserStatisticRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List userStatisticList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < userStatisticList.size(); i++)
			{
				UserStatistic userStatistic = (UserStatistic) userStatisticList.get(i);
				System.out.println("UserStatistic: " + userStatisticList.toString());
			}
		
			System.out.println("UserStatisticRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}