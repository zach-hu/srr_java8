package com.tsa.puridiom.costrange.tasks.tests;

import com.tsa.puridiom.costrange.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class CostRangeRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			CostRangeRetrieveBy test = new CostRangeRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "TESTUSER");
			incomingRequest.put("CostRange_itemType", "CAT");

			test.executeTask(incomingRequest);

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}