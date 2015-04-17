package com.tsa.puridiom.vendorregcommrel.tasks.tests;

import com.tsa.puridiom.vendorregcommrel.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class VendorRegCommRelDeleteByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			VendorRegCommRelDeleteByVendor test = new VendorRegCommRelDeleteByVendor();
			Map incomingRequest = new HashMap();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorRegCommRel_vendorId", "TESTVNDR");
		
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				//system.out.println("VendorRegCommRelDeleteByVendorIdTest SUCCESS");
				dbs.commit();
			}
		
			//system.out.println(incomingRequest);
			//system.out.println("VendorRegCommRelDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}