package com.tsa.puridiom.sungard.address.tasks.tests;

import com.tsa.puridiom.entity.sungard.Address;
import com.tsa.puridiom.sungard.address.tasks.SungardAddressRetrieveById;
import com.tsagate.foundation.database.DBSession;
import java.util.HashMap;
import java.util.Map;

public class SungardAddressRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardAddressRetrieveById test = new SungardAddressRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("Address_internalVendorId", "99999");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			Address address = (Address) test.executeTask(incomingRequest);
		
			System.out.println("Address: " + address.toString());
			System.out.println("AddressRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}