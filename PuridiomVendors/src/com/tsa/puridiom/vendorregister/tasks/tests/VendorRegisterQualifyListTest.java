package com.tsa.puridiom.vendorregister.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.vendorregister.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class VendorRegisterQualifyListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	organizationId = "PURIDIOM";
		    String	userId = "KELLI";
			DBSession dbs = new DBSession(organizationId);
			VendorRegisterQualifyList test = new VendorRegisterQualifyList();
			Map incomingRequest = new HashMap();
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", userId);
			incomingRequest.put("VendorRegister_vendorId", "TSATHS");
			
			test.executeTask(incomingRequest);
			
			dbs.commit();
			
			System.out.println("COMPLETE");			
			System.out.println("Database Status: " + dbs.getStatus());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}