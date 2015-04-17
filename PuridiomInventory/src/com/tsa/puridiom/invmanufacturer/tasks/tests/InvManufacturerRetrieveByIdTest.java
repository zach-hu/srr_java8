package com.tsa.puridiom.invmanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invmanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvManufacturerRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvManufacturerRetrieveById test = new InvManufacturerRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvManufacturer invManufacturer = (InvManufacturer) test.executeTask(incomingRequest);
		
			System.out.println("InvManufacturer: " + invManufacturer.toString());
			System.out.println("InvManufacturerRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}