package com.tsa.puridiom.inspectiondispos.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectiondispos.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InspectionDisposRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InspectionDisposRetrieveById test = new InspectionDisposRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			InspectionDispos inspectionDispos = (InspectionDispos) test.executeTask(incomingRequest);
		
			System.out.println("InspectionDispos: " + inspectionDispos.toString());
			System.out.println("InspectionDisposRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}