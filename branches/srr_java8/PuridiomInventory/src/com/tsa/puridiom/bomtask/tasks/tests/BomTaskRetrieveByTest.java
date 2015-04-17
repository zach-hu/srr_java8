package com.tsa.puridiom.bomtask.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomtask.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomTaskRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomTaskRetrieveBy test = new BomTaskRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List bomTaskList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < bomTaskList.size(); i++)
			{
				BomTask bomTask = (BomTask) bomTaskList.get(i);
				System.out.println("BomTask: " + bomTaskList.toString());
			}
		
			System.out.println("BomTaskRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}