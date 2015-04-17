package com.tsa.puridiom.bomrouting.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomrouting.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomRoutingRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomRoutingRetrieveBy test = new BomRoutingRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List bomRoutingList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < bomRoutingList.size(); i++)
			{
				BomRouting bomRouting = (BomRouting) bomRoutingList.get(i);
				System.out.println("BomRouting: " + bomRoutingList.toString());
			}
		
			System.out.println("BomRoutingRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}