package com.tsa.puridiom.sungard.keyid.tasks.tests;

import com.tsa.puridiom.sungard.keyid.tasks.GenerateInternalVendorId;
import com.tsagate.foundation.database.DBSession;
import java.util.HashMap;
import java.util.Map;

public class GenerateInternalVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			GenerateInternalVendorId test = new GenerateInternalVendorId();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			String internalVendorId = (String) test.executeTask(incomingRequest);
		
			System.out.println("internalVendorId: " + internalVendorId);
			System.out.println("GenerateInternalVendorId COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}