package com.tsa.puridiom.invmanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invmanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvManufacturerRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvManufacturerRetrieveAll test = new InvManufacturerRetrieveAll();
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
		
			System.out.println("InvManufacturerRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}