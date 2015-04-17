package com.tsa.puridiom.vendorinsurance.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorinsurance.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class VendorInsuranceRetrieveByVendorIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorInsuranceRetrieveByVendorId test = new VendorInsuranceRetrieveByVendorId();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorInsurance_vendorId", "STAPLES");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List vendorInsuranceList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorInsuranceList.size(); i++)
			{
				VendorInsurance vendorInsurance = (VendorInsurance) vendorInsuranceList.get(i);
				System.out.println("VendorInsurance: " + vendorInsuranceList.toString());
			}
		
			System.out.println("VendorInsuranceRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}