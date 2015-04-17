package com.tsa.puridiom.recentorder.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorder.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentOrderDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderDeleteById test = new RecentOrderDeleteById();
			Map incomingRequest = new HashMap();
		
			RecentOrder recentOrder = new RecentOrder();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentOrder_buyerCode", "KELLI");
			incomingRequest.put("RecentOrder_icPoHeader", "1234567892");
		
			recentOrder = (RecentOrder) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentOrderDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("RecentOrderDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}