package com.tsa.puridiom.rfqvendor.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqvendor.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqVendorRetrieveByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqVendorRetrieveByHeader test = new RfqVendorRetrieveByHeader();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqVendor_icRfqHeader", "602703700000");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List rfqVendorList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < rfqVendorList.size(); i++)
			{
				RfqVendor vendor = (RfqVendor) rfqVendorList.get(i);
				System.out.println("Rfq Vendor: " + rfqVendorList.toString());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}