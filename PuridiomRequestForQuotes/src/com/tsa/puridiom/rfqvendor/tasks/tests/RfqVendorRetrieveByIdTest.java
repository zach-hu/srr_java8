package com.tsa.puridiom.rfqvendor.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqvendor.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqVendorRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqVendorRetrieveById test = new RfqVendorRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqVendor_icRfqHeader", "827010200000");
			incomingRequest.put("RfqVendor_vendorId", "VENDOR-3");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			RfqVendor rfqVendor = (RfqVendor) test.executeTask(incomingRequest);
			System.out.println("Rfq Vendor: " + rfqVendor.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}