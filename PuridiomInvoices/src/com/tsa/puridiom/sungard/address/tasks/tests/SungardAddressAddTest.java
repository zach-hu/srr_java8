package com.tsa.puridiom.sungard.address.tasks.tests;

import com.tsa.puridiom.entity.sungard.Address;
import com.tsa.puridiom.sungard.address.tasks.SungardAddressAdd;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class SungardAddressAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("SUNGARDEAS");
			SungardAddressAdd test = new SungardAddressAdd();
			Map incomingRequest = new HashMap();
		
			Address address = new Address();
		
			address.setAddress1("Address Line 1");
			address.setAddress2("Address Line 2");
			address.setAddress3("Address Line 3");
			address.setAddressTypeInd("T");
			address.setAssociatedAddrId(new BigDecimal("0"));
			address.setCity("Testville");
			address.setCountryCode("USA");
			address.setInternalVendorId(new BigDecimal("99999"));
			
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "SUNGARDEAS");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("address", address);
		
			address = (Address) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("AddressAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("AddressAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}