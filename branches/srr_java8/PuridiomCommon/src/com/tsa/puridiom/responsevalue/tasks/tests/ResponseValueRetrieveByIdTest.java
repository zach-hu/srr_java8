package com.tsa.puridiom.responsevalue.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.responsevalue.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class ResponseValueRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ResponseValueRetrieveById test = new ResponseValueRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			ResponseValue responseValue = (ResponseValue) test.executeTask(incomingRequest);
		
			System.out.println("ResponseValue: " + responseValue.toString());
			System.out.println("ResponseValueRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}