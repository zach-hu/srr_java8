package com.tsa.puridiom.rfqbid.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqbid.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqBidRetrieveByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RfqBidRetrieveByLine test = new RfqBidRetrieveByLine();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqBid_icRfqLine", "1146136500000");
			
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