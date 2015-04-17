package com.tsa.puridiom.rfqvendor.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqvendor.tasks.*;
import com.tsagate.foundation.database.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqVendorUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqVendorUpdate test = new RfqVendorUpdate();
			Map incomingRequest = new HashMap();
			RfqVendor rfqVendor = new RfqVendor();
			RfqVendorPK pk = new RfqVendorPK();
			pk.setIcRfqHeader(new BigDecimal("602703700000"));
			pk.setVendorId("VENDOR-3");
			rfqVendor.setComp_id(pk);
			rfqVendor.setContactId("002");
			rfqVendor.setDiscountPercent(new BigDecimal("4"));
			rfqVendor.setDiscountAmount(new BigDecimal("28.00"));
			rfqVendor.setShippingCharges(new BigDecimal("12"));
			rfqVendor.setOtherCharges(new BigDecimal("3.50"));
			rfqVendor.setOtherDescription("This is an update test");
			rfqVendor.setTaxShipping("");
			rfqVendor.setTaxOther("");
			rfqVendor.setTaxCode("PA");
			//rfqVendor.setDatePromised(new Date("2004-02-01"));

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom4");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqVendor", rfqVendor);

			test.executeTask(incomingRequest);

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}