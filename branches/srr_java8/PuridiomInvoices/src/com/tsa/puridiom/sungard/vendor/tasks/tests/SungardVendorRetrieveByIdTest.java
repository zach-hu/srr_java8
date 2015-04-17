package com.tsa.puridiom.sungard.vendor.tasks.tests;

import com.tsa.puridiom.entity.sungard.Vendor;
import com.tsa.puridiom.sungard.vendor.tasks.SungardVendorRetrieveById;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class SungardVendorRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardVendorRetrieveById test = new SungardVendorRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("SungardVendor_vendorId", "TEST");
			incomingRequest.put("SungardVendor_internalVendorId", "99999");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			Vendor vendor = (Vendor) test.executeTask(incomingRequest);
			if (vendor == null) {
			    System.out.println("Vendor record not found.");
			} else {
			    System.out.println("Vendor: " + vendor.toString());
			}
		
			System.out.println("SungardVendorRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}