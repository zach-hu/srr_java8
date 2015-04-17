package com.tsa.puridiom.vendorcommrel.tasks.tests;

import com.tsa.puridiom.vendorcommrel.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorCommRelDeleteByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			VendorCommRelDeleteByVendor test = new VendorCommRelDeleteByVendor();
			Map incomingRequest = new HashMap();

			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("VendorCommRel_vendorId", "TULA");

			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
			System.out.println("VendorCommRelDeleteByVendorTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}