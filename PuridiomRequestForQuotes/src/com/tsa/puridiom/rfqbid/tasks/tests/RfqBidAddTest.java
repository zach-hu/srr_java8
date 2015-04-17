package com.tsa.puridiom.rfqbid.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqbid.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqBidAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqBidAdd test = new RfqBidAdd();
			Map incomingRequest = new HashMap();

			RfqBid rfqBid = new RfqBid();
			RfqBidPK pk = new RfqBidPK();
			pk.setIcRfqHeader(new BigDecimal("602703700000"));
			pk.setIcRfqLine(new BigDecimal("643991300000"));
			pk.setVendorId("VENDOR-TEST");
			rfqBid.setComp_id(pk);
			rfqBid.setQuantity(new BigDecimal("20"));
			rfqBid.setUnitPrice(new BigDecimal("15.99"));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqBid", rfqBid);

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