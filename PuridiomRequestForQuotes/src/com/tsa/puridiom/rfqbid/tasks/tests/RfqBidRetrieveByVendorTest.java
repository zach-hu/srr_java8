package com.tsa.puridiom.rfqbid.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqbid.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqBidRetrieveByVendorTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqBidRetrieveByHeader test = new RfqBidRetrieveByHeader();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqBid_icRfqHeader", "827010200000");
			incomingRequest.put("RfqBid_vendorId", "VENDOR-1");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List list = (List) test.executeTask(incomingRequest);
			for (int i=0; i < list.size(); i++)
			{
				RfqBid rfqBid = (RfqBid) list.get(i);
				System.out.println("Rfq Bid: " + rfqBid.toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}