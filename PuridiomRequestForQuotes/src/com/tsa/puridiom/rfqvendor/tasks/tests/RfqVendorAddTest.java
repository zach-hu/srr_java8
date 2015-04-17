package com.tsa.puridiom.rfqvendor.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqvendor.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqVendorAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqVendorAdd test = new RfqVendorAdd();
			Map incomingRequest = new HashMap();

			RfqVendor rfqVendor = new RfqVendor();
			RfqVendorPK pk = new RfqVendorPK();
			pk.setIcRfqHeader(new BigDecimal("602703700000"));
			pk.setVendorId("VENDOR-TEST");
			rfqVendor.setComp_id(pk);
			rfqVendor.setContactId("001");
			rfqVendor.setDiscountPercent(new BigDecimal("4"));
			rfqVendor.setDiscountAmount(new BigDecimal("28.00"));
			rfqVendor.setShippingCharges(new BigDecimal("12"));
			rfqVendor.setOtherCharges(new BigDecimal("3.50"));
			rfqVendor.setOtherDescription("This is a test vendor add.");

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqVendor", rfqVendor);

			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}