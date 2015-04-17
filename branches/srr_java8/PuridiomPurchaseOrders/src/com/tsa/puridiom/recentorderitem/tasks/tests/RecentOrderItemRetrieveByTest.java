package com.tsa.puridiom.recentorderitem.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorderitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class RecentOrderItemRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderItemRetrieveBy test = new RecentOrderItemRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentOrderItem_buyerCode", "KELLI");
			
			System.out.println("Database Status: " + dbs.getStatus());
		
			List recentOrderItemList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < recentOrderItemList.size(); i++)
			{
				RecentOrderItem recentOrderItem = (RecentOrderItem) recentOrderItemList.get(i);
				System.out.println("RecentOrderItem: " + recentOrderItemList.toString());
			}
		
			System.out.println("RecentOrderItemRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}