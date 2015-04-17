package com.tsa.puridiom.invworkcenter.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invworkcenter.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvWorkCenterRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvWorkCenterRetrieveBy test = new InvWorkCenterRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List invWorkCenterList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < invWorkCenterList.size(); i++)
			{
				InvWorkCenter invWorkCenter = (InvWorkCenter) invWorkCenterList.get(i);
				System.out.println("InvWorkCenter: " + invWorkCenterList.toString());
			}
		
			System.out.println("InvWorkCenterRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}