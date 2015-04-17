package com.tsa.puridiom.inspectiondispos.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.inspectiondispos.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class InspectionDisposDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			InspectionDisposDeleteById test = new InspectionDisposDeleteById();
			Map incomingRequest = new HashMap();
		
			InspectionDispos inspectionDispos = new InspectionDispos();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("inspectionDispos", inspectionDispos);
		
			inspectionDispos = (InspectionDispos) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("InspectionDisposDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("InspectionDisposDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}