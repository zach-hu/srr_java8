package com.tsa.puridiom.invworkcenter.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.invworkcenter.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InvWorkCenterAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			InvWorkCenterAdd test = new InvWorkCenterAdd();
			Map incomingRequest = new HashMap();
		
			InvWorkCenter invWorkCenter = new InvWorkCenter();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("invWorkCenter", invWorkCenter);
		
			invWorkCenter = (InvWorkCenter) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InvWorkCenterAddTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InvWorkCenterAddTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}