package com.tsa.puridiom.rfqvendor.tasks.tests;

import com.tsa.puridiom.rfqvendor.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqVendorDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqVendorDeleteById test = new RfqVendorDeleteById();
			Map incomingRequest = new HashMap();

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqVendor_icRfqHeader", "827010200000");
			incomingRequest.put("RfqVendor_vendorId", "VENDOR-TEST");

			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}