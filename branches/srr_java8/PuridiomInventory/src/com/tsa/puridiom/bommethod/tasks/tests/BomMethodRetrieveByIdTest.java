package com.tsa.puridiom.bommethod.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommethod.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomMethodRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomMethodRetrieveById test = new BomMethodRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			BomMethod bomMethod = (BomMethod) test.executeTask(incomingRequest);
		
			System.out.println("BomMethod: " + bomMethod.toString());
			System.out.println("BomMethodRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}