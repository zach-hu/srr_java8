package com.tsa.puridiom.rfqline.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqline.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqLineRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqLineRetrieveById test = new RfqLineRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqLine_icRfqLine", "643991300000");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			RfqLine rfqLine = (RfqLine) test.executeTask(incomingRequest);
			System.out.println("Rfq Line: " + rfqLine.toString());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}