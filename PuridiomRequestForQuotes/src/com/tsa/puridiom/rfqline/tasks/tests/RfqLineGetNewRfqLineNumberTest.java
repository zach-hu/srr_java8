package com.tsa.puridiom.rfqline.tasks.tests;

import com.tsa.puridiom.rfqline.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqLineGetNewRfqLineNumberTest
{
	public static void  main (String[] args)
	{
		try
		{
			RfqLineGetNewRfqLineNumber getLineNumber = new RfqLineGetNewRfqLineNumber();
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("dbsession", new DBSession("PURIDIOM"));
			incomingRequest.put("RfqHeader_icRfqHeader","602703700000") ;
			
			Object obj = getLineNumber.executeTask(incomingRequest);
			
			System.out.println("Object: " + obj.toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}