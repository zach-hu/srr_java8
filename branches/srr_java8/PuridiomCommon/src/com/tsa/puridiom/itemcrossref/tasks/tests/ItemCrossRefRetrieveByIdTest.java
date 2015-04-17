package com.tsa.puridiom.itemcrossref.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.itemcrossref.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ItemCrossRefRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ItemCrossRefRetrieveById test = new ItemCrossRefRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			ItemCrossRef itemCrossRef = (ItemCrossRef) test.executeTask(incomingRequest);
		
			System.out.println("ItemCrossRef: " + itemCrossRef.toString());
			System.out.println("ItemCrossRefRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}