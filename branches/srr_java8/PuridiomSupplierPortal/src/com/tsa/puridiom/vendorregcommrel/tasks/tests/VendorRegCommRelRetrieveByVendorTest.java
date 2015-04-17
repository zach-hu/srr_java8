package com.tsa.puridiom.vendorregcommrel.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorregcommrel.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class VendorRegCommRelRetrieveByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			VendorRegCommRelRetrieveByVendor test = new VendorRegCommRelRetrieveByVendor();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorRegCommRel_vendorId", "TESTVNDR");
			
			//system.out.println("Database Status: " + dbs.getStatus());
		
			List vendorRegCommRelList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < vendorRegCommRelList.size(); i++)
			{
				VendorRegCommRel vendorRegCommRel = (VendorRegCommRel) vendorRegCommRelList.get(i);
				//system.out.println("VendorRegCommRel: " + vendorRegCommRelList.toString());
			}
		
			//system.out.println("VendorRegCommRelRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}