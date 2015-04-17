package com.tsa.puridiom.rfqvendor.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqvendor.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqVendorRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqVendorRetrieveBy test = new RfqVendorRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqVendor_icRfqHeader", "602703700000");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List list = (List) test.executeTask(incomingRequest);

			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					RfqVendor rfqVendor = (RfqVendor) list.get(i);
					System.out.println("RFQ VENDOR: " + rfqVendor.toString());
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}