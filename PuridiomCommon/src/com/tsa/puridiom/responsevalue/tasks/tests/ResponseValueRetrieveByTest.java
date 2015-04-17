package com.tsa.puridiom.responsevalue.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.responsevalue.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class ResponseValueRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ResponseValueRetrieveByQuestion test = new ResponseValueRetrieveByQuestion();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List responseValueList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < responseValueList.size(); i++)
			{
				ResponseValue responseValue = (ResponseValue) responseValueList.get(i);
				System.out.println("ResponseValue: " + responseValueList.toString());
			}
		
			System.out.println("ResponseValueRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}