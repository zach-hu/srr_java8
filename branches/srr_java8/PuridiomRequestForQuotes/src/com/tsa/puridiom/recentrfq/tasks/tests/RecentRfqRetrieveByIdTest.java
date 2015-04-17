package com.tsa.puridiom.recentrfq.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentrfq.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentRfqRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentRfqRetrieveById test = new RecentRfqRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			RecentRfq recentRfq = (RecentRfq) test.executeTask(incomingRequest);
		
			System.out.println("RecentRfq: " + recentRfq.toString());
			System.out.println("RecentRfqRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}