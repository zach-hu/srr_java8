package com.tsa.puridiom.rfqbid.tasks.tests;

import com.tsa.puridiom.rfqbid.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqBidDeleteByLineTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqBidDeleteByLine test = new RfqBidDeleteByLine();
			Map incomingRequest = new HashMap();

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqBid_icRfqHeader", "816401800000");
			incomingRequest.put("RfqBid_icRfqLine", "816614100000");

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