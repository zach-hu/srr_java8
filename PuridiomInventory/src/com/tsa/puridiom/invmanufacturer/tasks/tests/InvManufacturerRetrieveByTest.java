package com.tsa.puridiom.invmanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invmanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvManufacturerRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvManufacturerRetrieveBy test = new InvManufacturerRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invManufacturerList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invManufacturerList.size(); i++)
			{
				InvManufacturer invManufacturer = (InvManufacturer) invManufacturerList.get(i);
				System.out.println("InvManufacturer: " + invManufacturerList.toString());
			}
		
			System.out.println("InvManufacturerRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}