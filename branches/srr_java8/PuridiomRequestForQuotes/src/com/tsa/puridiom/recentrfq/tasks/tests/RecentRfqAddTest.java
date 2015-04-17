package com.tsa.puridiom.recentrfq.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentrfq.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentRfqAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentRfqAdd test = new RecentRfqAdd();
			Map incomingRequest = new HashMap();
		
			RecentRfq recentRfq = new RecentRfq();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("recentRfq", recentRfq);
		
			recentRfq = (RecentRfq) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentRfqAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("RecentRfqAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}