package com.tsa.puridiom.inspectiondispos.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectiondispos.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InspectionDisposRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InspectionDisposRetrieveBy test = new InspectionDisposRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List inspectionDisposList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < inspectionDisposList.size(); i++)
			{
				InspectionDispos inspectionDispos = (InspectionDispos) inspectionDisposList.get(i);
				System.out.println("InspectionDispos: " + inspectionDisposList.toString());
			}
		
			System.out.println("InspectionDisposRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}