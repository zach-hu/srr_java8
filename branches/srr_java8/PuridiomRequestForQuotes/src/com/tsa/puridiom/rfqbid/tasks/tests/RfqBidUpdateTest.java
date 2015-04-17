package com.tsa.puridiom.rfqbid.tasks.tests;

import com.tsa.puridiom.rfqbid.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqBidUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RfqBidUpdate test = new RfqBidUpdate();
			Map incomingRequest = new HashMap();

			String	icRfqHeader[] = {"602703700000", "602703700000", "602703700000","602703700000", "602703700000", "602703700000"};
			String	icRfqLine[] = {"643991300000","643913100000","644284700000","643991300000","643913100000","644284700000"};
			String	vendorId[] = {"VENDOR-3","VENDOR-3","VENDOR-3", "VENDOR-9999", "VENDOR-9999", "VENDOR-9999"};
			String	unitPrice[] = {"105.50", "33.55", "9.29","100.50", "30.55", "8.09"};

			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqBid_icRfqHeader", icRfqHeader);
			incomingRequest.put("RfqBid_icRfqLine", icRfqLine);
			incomingRequest.put("RfqBid_vendorId", vendorId);
			incomingRequest.put("RfqBid_unitPrice", unitPrice);
			
			test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}