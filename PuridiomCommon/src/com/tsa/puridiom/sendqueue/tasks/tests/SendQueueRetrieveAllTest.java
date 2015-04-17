package com.tsa.puridiom.sendqueue.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.sendqueue.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class SendQueueRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			SendQueueRetrieveAll test = new SendQueueRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List sendQueueList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < sendQueueList.size(); i++)
			{
				SendQueue sendQueue = (SendQueue) sendQueueList.get(i);
				System.out.println("SendQueue: " + sendQueueList.toString());
			}
		
			System.out.println("SendQueueRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}