package com.tsa.puridiom.sungard.vendorcontacts.tasks.tests;

import com.tsa.puridiom.entity.sungard.VendorContacts;
import com.tsa.puridiom.sungard.vendorcontacts.tasks.SungardVendorContactsRetrieveById;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class SungardVendorContactsRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardVendorContactsRetrieveById test = new SungardVendorContactsRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("VendorContacts_internalVendorId", "99999");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			VendorContacts vendorContacts = (VendorContacts) test.executeTask(incomingRequest);
		
			System.out.println("VendorContacts: " + vendorContacts.toString());
			System.out.println("SungardVendorContactsRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}