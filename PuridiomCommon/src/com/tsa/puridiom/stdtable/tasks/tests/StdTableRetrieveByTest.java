package com.tsa.puridiom.stdtable.tasks.tests;

import com.tsa.puridiom.stdtable.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class StdTableRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdTableRetrieveBy test = new StdTableRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "TESTUSER");
			incomingRequest.put("StdTable_tableType", "SHP");
		
			test.executeTask(incomingRequest);
		
			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}