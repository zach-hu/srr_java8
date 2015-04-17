package com.tsa.puridiom.recentreqitem.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentreqitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentReqItemDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentReqItemDeleteById test = new RecentReqItemDeleteById();
			Map incomingRequest = new HashMap();
		
			RecentReqItem recentReqItem = new RecentReqItem();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentReqItem_requisitionerCode", "KELLI");
			incomingRequest.put("RecentReqItem_itemNumber", "1835000");
			incomingRequest.put("RecentReqItem_itemLocation", "COMPUSA");
		
			recentReqItem = (RecentReqItem) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("RecentReqItemDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("RecentReqItemDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}