package com.tsa.puridiom.commodity.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.commodity.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class CommodityRetrieveUNSPSCTreeTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			CommodityRetrieveUNSPSCTree test = new CommodityRetrieveUNSPSCTree();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			String	commodities[] = {"44122119", "86000000"};
			incomingRequest.put("Commodity_commodity", commodities);
		//	incomingRequest.put("Commodity_commodity", "44122119");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List commodityList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < commodityList.size(); i++)
			{
				Commodity commodity = (Commodity) commodityList.get(i);
				System.out.println("Commodity: " + commodity.toString());
			}
		
			System.out.println("Returned " + commodityList.size() + " rows.");
			System.out.println("CommodityRetrieveUNSPSCTreeTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}