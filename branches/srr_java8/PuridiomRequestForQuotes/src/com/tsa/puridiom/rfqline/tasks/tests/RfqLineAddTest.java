package com.tsa.puridiom.rfqline.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqline.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqLineAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqLineAdd test = new RfqLineAdd();
			Map incomingRequest = new HashMap();

			RfqLine rfqLine = new RfqLine();
			rfqLine.setIcRfqHeader(new BigDecimal("602703700000"));
			rfqLine.setIcRfqLine(new BigDecimal("602703700222"));
			rfqLine.setItemNumber("TEST-ITEM2");
			rfqLine.setRfqLine(new BigDecimal("2"));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqLine", rfqLine);

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