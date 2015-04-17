package com.tsa.puridiom.recentreqitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentreqitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentReqItemRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentReqItemRetrieveById test = new RecentReqItemRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentReqItem_requisitionerCode", "KELLI");
			incomingRequest.put("RecentReqItem_itemNumber", "1835000");
			incomingRequest.put("RecentReqItem_itemLocation", "COMPUSA");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			RecentReqItem recentReqItem = (RecentReqItem) test.executeTask(incomingRequest);
		
			System.out.println("RecentReqItem: " + recentReqItem.toString());
			System.out.println("RecentReqItemRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}