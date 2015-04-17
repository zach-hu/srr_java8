package com.tsa.puridiom.bommethod.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bommethod.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomMethodRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomMethodRetrieveBy test = new BomMethodRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List bomMethodList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < bomMethodList.size(); i++)
			{
				BomMethod bomMethod = (BomMethod) bomMethodList.get(i);
				System.out.println("BomMethod: " + bomMethodList.toString());
			}
		
			System.out.println("BomMethodRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}