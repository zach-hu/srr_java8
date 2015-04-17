package com.tsa.puridiom.rfq.tasks.tests;

import com.tsa.puridiom.rfq.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class RfqAutoNumberTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			RfqAutoNumber task = new RfqAutoNumber();
			DBSession dbsession = new DBSession("puridiom");
			Map incomingRequest = new HashMap();
			incomingRequest.put("dbsession", dbsession);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqHeader_fiscalYear", "2003");
			
			String number = (String) task.executeTask(incomingRequest);
			System.out.println(number);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}