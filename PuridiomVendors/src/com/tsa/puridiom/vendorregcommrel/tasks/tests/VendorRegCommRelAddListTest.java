package com.tsa.puridiom.vendorregcommrel.tasks.tests;

import com.tsa.puridiom.vendorregcommrel.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorRegCommRelAddListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorRegCommRelAddList test = new VendorRegCommRelAddList();
			Map incomingRequest = new HashMap();
			String	 commodities[] = {"TEST", "OFFICE"};
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorRegCommRel_commodityCode", commodities);
			incomingRequest.put("VendorRegCommRel_vendorId", "TESTVNDR");

			List list = (List) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				//system.out.println("VendorRegCommRelAddListTest SUCCESS");
				dbs.commit();
			}
		
			//system.out.println(incomingRequest);
			//system.out.println("VendorRegCommRelAddListTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}