package com.tsa.puridiom.itemcrossref.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.itemcrossref.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ItemCrossRefRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ItemCrossRefRetrieveBy test = new ItemCrossRefRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List itemCrossRefList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < itemCrossRefList.size(); i++)
			{
				ItemCrossRef itemCrossRef = (ItemCrossRef) itemCrossRefList.get(i);
				System.out.println("ItemCrossRef: " + itemCrossRefList.toString());
			}
		
			System.out.println("ItemCrossRefRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}