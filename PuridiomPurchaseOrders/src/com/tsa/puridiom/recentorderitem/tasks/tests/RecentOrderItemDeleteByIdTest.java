package com.tsa.puridiom.recentorderitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentorderitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentOrderItemDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentOrderItemDeleteById test = new RecentOrderItemDeleteById();
			Map incomingRequest = new HashMap();
		
			RecentOrderItem recentOrderItem = new RecentOrderItem();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentOrderItem_buyerCode", "KELLI");
			incomingRequest.put("RecentOrderItem_itemNumber", "1835000");
			incomingRequest.put("RecentOrderItem_itemLocation", "COMPUSA");
		
			recentOrderItem = (RecentOrderItem) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentOrderItemDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("RecentOrderItemDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}