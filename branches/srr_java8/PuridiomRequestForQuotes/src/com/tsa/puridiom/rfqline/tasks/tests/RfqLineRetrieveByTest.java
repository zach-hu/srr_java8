package com.tsa.puridiom.rfqline.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqline.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqLineRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqLineRetrieveBy test = new RfqLineRetrieveBy();
			Map incomingRequest = new HashMap();

			incomingRequest.put("RfqLine_icRfqHeader", "602703700000");
			incomingRequest.put("dbsession", dbs);

			System.out.println("Database Status: " + dbs.getStatus());

			List list = (List) test.executeTask(incomingRequest);
			if (list != null)
			{
				for (int i = 0; i < list.size(); i++)
				{
					RfqLine rfqLine = (RfqLine) list.get(i);
					System.out.println(rfqLine.toString());
				}
			}

			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}