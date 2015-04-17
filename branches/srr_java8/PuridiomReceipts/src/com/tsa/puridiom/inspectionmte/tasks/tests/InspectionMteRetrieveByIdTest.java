package com.tsa.puridiom.inspectionmte.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectionmte.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InspectionMteRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InspectionMteRetrieveById test = new InspectionMteRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InspectionMte inspectionMte = (InspectionMte) test.executeTask(incomingRequest);
		
			System.out.println("InspectionMte: " + inspectionMte.toString());
			System.out.println("InspectionMteRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}