package com.tsa.puridiom.itemcrossref.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.itemcrossref.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ItemCrossRefAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ItemCrossRefAdd test = new ItemCrossRefAdd();
			Map incomingRequest = new HashMap();
		
			ItemCrossRef itemCrossRef = new ItemCrossRef();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("itemCrossRef", itemCrossRef);
		
			itemCrossRef = (ItemCrossRef) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("ItemCrossRefAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("ItemCrossRefAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}