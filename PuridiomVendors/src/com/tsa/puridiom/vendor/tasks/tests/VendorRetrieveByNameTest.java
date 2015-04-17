package com.tsa.puridiom.vendor.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendor.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorRetrieveByNameTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			VendorRetrieveByName test = new VendorRetrieveByName();
			Map incomingRequest = new HashMap();

			incomingRequest.put("Vendor_vendorName", "Technical Services Associates");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List vendorList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorList.size(); i++)
			{
				Vendor vendor = (Vendor) vendorList.get(i);
				System.out.println("Vendor: " + vendor.toString());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}