package com.tsa.puridiom.inspectionmte.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectionmte.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InspectionMteUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InspectionMteUpdate test = new InspectionMteUpdate();
			Map incomingRequest = new HashMap();
		
			InspectionMte inspectionMte = new InspectionMte();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("inspectionMte", inspectionMte);
		
			inspectionMte = (InspectionMte) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InspectionMteUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InspectionMteUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}