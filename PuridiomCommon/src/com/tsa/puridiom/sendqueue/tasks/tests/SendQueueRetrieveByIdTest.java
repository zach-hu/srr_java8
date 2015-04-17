package com.tsa.puridiom.sendqueue.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.sendqueue.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class SendQueueRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			SendQueueRetrieveById test = new SendQueueRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			SendQueue sendQueue = (SendQueue) test.executeTask(incomingRequest);
		
			System.out.println("SendQueue: " + sendQueue.toString());
			System.out.println("SendQueueRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}