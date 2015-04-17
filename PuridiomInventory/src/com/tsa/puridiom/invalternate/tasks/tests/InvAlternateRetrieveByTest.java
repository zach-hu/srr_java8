package com.tsa.puridiom.invalternate.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invalternate.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvAlternateRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvAlternateRetrieveBy test = new InvAlternateRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invAlternateList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invAlternateList.size(); i++)
			{
				InvAlternate invAlternate = (InvAlternate) invAlternateList.get(i);
				System.out.println("InvAlternate: " + invAlternateList.toString());
			}
		
			System.out.println("InvAlternateRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}