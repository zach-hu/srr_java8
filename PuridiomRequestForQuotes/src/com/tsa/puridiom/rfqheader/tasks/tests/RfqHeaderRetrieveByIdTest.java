package com.tsa.puridiom.rfqheader.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqheader.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqHeaderRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqHeaderRetrieveById test = new RfqHeaderRetrieveById();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqHeader_icRfqHeader", "99999900000");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			RfqHeader rfqHeader = (RfqHeader) test.executeTask(incomingRequest);
			System.out.println("Rfq Header: " + rfqHeader.toString());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}