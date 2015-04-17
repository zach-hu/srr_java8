package com.tsa.puridiom.invalternate.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invalternate.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvAlternateRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvAlternateRetrieveById test = new InvAlternateRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvAlternate invAlternate = (InvAlternate) test.executeTask(incomingRequest);
		
			System.out.println("InvAlternate: " + invAlternate.toString());
			System.out.println("InvAlternateRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}