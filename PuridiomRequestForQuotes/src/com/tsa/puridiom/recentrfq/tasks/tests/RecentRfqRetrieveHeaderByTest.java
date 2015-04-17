package com.tsa.puridiom.recentrfq.tasks.tests;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.recentrfq.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecentRfqRetrieveHeaderByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentRfqRetrieveHeaderBy test = new RecentRfqRetrieveHeaderBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentRfq_buyerCode", "KELLI");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List recentRfqList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < recentRfqList.size(); i++)
			{
			    RfqHeader rfqHeader = (RfqHeader) recentRfqList.get(i);
				System.out.println("RfqHeader: " + rfqHeader.toString());
			}
		
			System.out.println("RecentOrderRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}