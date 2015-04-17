package com.tsa.puridiom.bommanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomManufacturerDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomManufacturerDeleteById test = new BomManufacturerDeleteById();
			Map incomingRequest = new HashMap();
		
			BomManufacturer bomManufacturer = new BomManufacturer();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomManufacturer", bomManufacturer);
		
			bomManufacturer = (BomManufacturer) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomManufacturerDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomManufacturerDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}