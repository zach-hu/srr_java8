package com.tsa.puridiom.bomcomponent.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomcomponent.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomComponentRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomComponentRetrieveById test = new BomComponentRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			BomComponent bomComponent = (BomComponent) test.executeTask(incomingRequest);
		
			System.out.println("BomComponent: " + bomComponent.toString());
			System.out.println("BomComponentRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}