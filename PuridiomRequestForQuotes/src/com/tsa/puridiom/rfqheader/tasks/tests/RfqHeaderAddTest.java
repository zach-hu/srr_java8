package com.tsa.puridiom.rfqheader.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqheader.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqHeaderAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqHeaderAdd test = new RfqHeaderAdd();
			Map incomingRequest = new HashMap();

			RfqHeader rfqHeader = new RfqHeader();
			rfqHeader.setIcRfqHeader(new BigDecimal("99999900000"));
			rfqHeader.setBuyer("KELLI");
			rfqHeader.setLastChgBy("TEST");

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqHeader", rfqHeader);

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