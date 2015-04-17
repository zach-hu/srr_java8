package com.tsa.puridiom.invmethod.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invmethod.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvMethodRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvMethodRetrieveById test = new InvMethodRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvMethod invMethod = (InvMethod) test.executeTask(incomingRequest);
		
			System.out.println("InvMethod: " + invMethod.toString());
			System.out.println("InvMethodRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}