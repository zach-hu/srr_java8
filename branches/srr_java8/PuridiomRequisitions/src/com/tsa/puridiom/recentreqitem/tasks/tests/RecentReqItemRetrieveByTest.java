package com.tsa.puridiom.recentreqitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentreqitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentReqItemRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentReqItemRetrieveBy test = new RecentReqItemRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentReqItem_requisitionerCode", "KELLI");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List recentReqItemList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < recentReqItemList.size(); i++)
			{
				RecentReqItem recentReqItem = (RecentReqItem) recentReqItemList.get(i);
				System.out.println("RecentReqItem: " + recentReqItemList.toString());
			}
		
			System.out.println("RecentReqItemRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}