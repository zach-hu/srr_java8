package com.tsa.puridiom.invworkcenter.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invworkcenter.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvWorkCenterRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvWorkCenterRetrieveById test = new InvWorkCenterRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InvWorkCenter invWorkCenter = (InvWorkCenter) test.executeTask(incomingRequest);
		
			System.out.println("InvWorkCenter: " + invWorkCenter.toString());
			System.out.println("InvWorkCenterRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}