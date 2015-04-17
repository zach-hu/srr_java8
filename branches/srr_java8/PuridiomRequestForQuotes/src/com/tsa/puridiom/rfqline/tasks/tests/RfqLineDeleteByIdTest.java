package com.tsa.puridiom.rfqline.tasks.tests;

import com.tsa.puridiom.rfqline.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqLineDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqLineDeleteById test = new RfqLineDeleteById();
			Map incomingRequest = new HashMap();

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqLine_icRfqHeader", "602703700000");
			incomingRequest.put("RfqLine_icRfqLine", "602703700222");

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