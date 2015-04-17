package com.tsa.puridiom.bommanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomManufacturerRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomManufacturerRetrieveById test = new BomManufacturerRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			BomManufacturer bomManufacturer = (BomManufacturer) test.executeTask(incomingRequest);
		
			System.out.println("BomManufacturer: " + bomManufacturer.toString());
			System.out.println("BomManufacturerRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}