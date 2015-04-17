package com.tsa.puridiom.invmanufacturer.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invmanufacturer.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvManufacturerAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvManufacturerAdd test = new InvManufacturerAdd();
			Map incomingRequest = new HashMap();
		
			InvManufacturer invManufacturer = new InvManufacturer();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invManufacturer", invManufacturer);
		
			invManufacturer = (InvManufacturer) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvManufacturerAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvManufacturerAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}