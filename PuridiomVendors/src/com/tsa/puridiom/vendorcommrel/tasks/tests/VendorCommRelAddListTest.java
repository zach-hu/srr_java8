package com.tsa.puridiom.vendorcommrel.tasks.tests;

import com.tsa.puridiom.vendorcommrel.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorCommRelAddListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorCommRelAddList test = new VendorCommRelAddList();
			Map incomingRequest = new HashMap();
			String	commodities[] = {"COMMODITY1", "COMMODITY2"};
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorCommRel_vendorId", "TESTVENDOR");
			incomingRequest.put("VendorCommRel_commodityCode", commodities);
			
			//test.setApplicationName("bidboard");
			List vendorCommRelList = (List) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorCommRelAddListTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("VendorCommRelAddListTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}