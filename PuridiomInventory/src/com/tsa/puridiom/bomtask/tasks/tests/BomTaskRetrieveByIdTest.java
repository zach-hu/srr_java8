package com.tsa.puridiom.bomtask.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomtask.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomTaskRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomTaskRetrieveById test = new BomTaskRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			BomTask bomTask = (BomTask) test.executeTask(incomingRequest);
		
			System.out.println("BomTask: " + bomTask.toString());
			System.out.println("BomTaskRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}