package com.tsa.puridiom.vendorinsurance.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorinsurance.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class VendorInsuranceRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorInsuranceRetrieveById test = new VendorInsuranceRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			VendorInsurance vendorInsurance = (VendorInsurance) test.executeTask(incomingRequest);
		
			System.out.println("VendorInsurance: " + vendorInsurance.toString());
			System.out.println("VendorInsuranceRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}