package com.tsa.puridiom.rfqbid.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqbid.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqBidRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqBidRetrieveById test = new RfqBidRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqBid_icRfqHeader", "827010200000");
			incomingRequest.put("RfqBid_icRfqLine", "1146136500000");
			incomingRequest.put("RfqBid_vendorId", "VENDOR-3");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			RfqBid rfqBid = (RfqBid) test.executeTask(incomingRequest);
			System.out.println("Rfq Bid: " + rfqBid.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}