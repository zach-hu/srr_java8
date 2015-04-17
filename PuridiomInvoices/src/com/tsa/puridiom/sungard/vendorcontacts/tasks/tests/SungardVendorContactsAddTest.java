package com.tsa.puridiom.sungard.vendorcontacts.tasks.tests;

import com.tsa.puridiom.entity.sungard.VendorContacts;
import com.tsa.puridiom.sungard.vendorcontacts.tasks.SungardVendorContactsAdd;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SungardVendorContactsAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardVendorContactsAdd test = new SungardVendorContactsAdd();
			Map incomingRequest = new HashMap();
		
			VendorContacts vendorContacts = new VendorContacts();
		
			vendorContacts.setInternalVendorId(new BigDecimal("99999"));
			vendorContacts.setAssociatedAddrId(new BigDecimal("0"));
			vendorContacts.setContactEmailAddr("test@contacts.com");
			vendorContacts.setContactFaxNum("999-999-9999");
			vendorContacts.setContactName("Test Contact");
			vendorContacts.setContactPhoneNum("111-111-1111");
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("vendorContacts", vendorContacts);
		
			vendorContacts = (VendorContacts) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("VendorContactsAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("SungardVendorContactsAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}