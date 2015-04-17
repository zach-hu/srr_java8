package com.tsa.puridiom.bomcomponent.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.bomcomponent.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class BomComponentRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("HILTON");
			BomComponentRetrieveAll test = new BomComponentRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "HILTON");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List bomComponentList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < bomComponentList.size(); i++)
			{
				BomComponent bomComponent = (BomComponent) bomComponentList.get(i);
				System.out.println("BomComponent: " + bomComponentList.toString());
			}
		
			System.out.println("BomComponentRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}