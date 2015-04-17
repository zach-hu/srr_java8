package com.tsa.puridiom.bommanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomManufacturerAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomManufacturerAdd test = new BomManufacturerAdd();
			Map incomingRequest = new HashMap();
		
			BomManufacturer bomManufacturer = new BomManufacturer();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("bomManufacturer", bomManufacturer);
		
			bomManufacturer = (BomManufacturer) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("BomManufacturerAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("BomManufacturerAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}