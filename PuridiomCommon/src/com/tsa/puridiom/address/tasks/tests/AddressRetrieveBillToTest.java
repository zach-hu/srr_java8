package com.tsa.puridiom.address.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.address.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class AddressRetrieveBillToTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			AddressRetrieveBillTo test = new AddressRetrieveBillTo();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("Address_addressCode", "NORTH");
			
			Address address = (Address) test.executeTask(incomingRequest);
			System.out.println("Database Status: " + dbs.getStatus());
			
			System.out.println("Address: " + address.toString());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}