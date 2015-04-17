package com.tsa.puridiom.bommanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomManufacturerRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomManufacturerRetrieveBy test = new BomManufacturerRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List bomManufacturerList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < bomManufacturerList.size(); i++)
			{
				BomManufacturer bomManufacturer = (BomManufacturer) bomManufacturerList.get(i);
				System.out.println("BomManufacturer: " + bomManufacturerList.toString());
			}
		
			System.out.println("BomManufacturerRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}