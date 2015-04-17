package com.tsa.puridiom.inspectionmte.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectionmte.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InspectionMteRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InspectionMteRetrieveAll test = new InspectionMteRetrieveAll();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List inspectionMteList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < inspectionMteList.size(); i++)
			{
				InspectionMte inspectionMte = (InspectionMte) inspectionMteList.get(i);
				System.out.println("InspectionMte: " + inspectionMteList.toString());
			}
		
			System.out.println("InspectionMteRetrieveAllTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}