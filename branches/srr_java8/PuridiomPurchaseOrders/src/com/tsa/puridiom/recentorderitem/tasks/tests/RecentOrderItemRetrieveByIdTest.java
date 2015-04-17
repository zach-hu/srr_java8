package com.tsa.puridiom.recentorderitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorderitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class RecentOrderItemRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderItemRetrieveById test = new RecentOrderItemRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentOrderItem_buyerCode", "KELLI");
			incomingRequest.put("RecentOrderItem_itemNumber", "1807600");
			incomingRequest.put("RecentOrderItem_itemLocation", "COMPUSA");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			RecentOrderItem recentOrderItem = (RecentOrderItem) test.executeTask(incomingRequest);
		
			System.out.println("RecentOrderItem: " + recentOrderItem.toString());
			System.out.println("RecentOrderItemRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}