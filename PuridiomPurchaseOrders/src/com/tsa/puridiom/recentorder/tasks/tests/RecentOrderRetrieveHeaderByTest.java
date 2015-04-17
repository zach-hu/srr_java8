package com.tsa.puridiom.recentorder.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorder.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class RecentOrderRetrieveHeaderByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderRetrieveHeaderBy test = new RecentOrderRetrieveHeaderBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentOrder_buyerCode", "KELLI");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List recentOrderList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < recentOrderList.size(); i++)
			{
				PoHeader poHeader = (PoHeader) recentOrderList.get(i);
				System.out.println("PoHeader: " + poHeader.toString());
			}
		
			System.out.println("RecentOrderRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}