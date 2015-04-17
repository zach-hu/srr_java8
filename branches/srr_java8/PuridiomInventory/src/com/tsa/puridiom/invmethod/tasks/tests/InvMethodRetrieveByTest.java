package com.tsa.puridiom.invmethod.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invmethod.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvMethodRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvMethodRetrieveBy test = new InvMethodRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invMethodList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invMethodList.size(); i++)
			{
				InvMethod invMethod = (InvMethod) invMethodList.get(i);
				System.out.println("InvMethod: " + invMethodList.toString());
			}
		
			System.out.println("InvMethodRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}