package com.tsa.puridiom.bomrouting.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomrouting.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomRoutingRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomRoutingRetrieveById test = new BomRoutingRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			BomRouting bomRouting = (BomRouting) test.executeTask(incomingRequest);
		
			System.out.println("BomRouting: " + bomRouting.toString());
			System.out.println("BomRoutingRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}