package com.tsa.puridiom.recentrequisition.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentrequisition.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;

import java.math.BigDecimal;
import java.util.*;

public class RecentRequisitionDeleteByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentRequisitionDeleteById test = new RecentRequisitionDeleteById();
			Map incomingRequest = new HashMap();
		
			RecentRequisition recentRequisition = new RecentRequisition();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentRequisition_icReqHeader", "1234567891");
			incomingRequest.put("RecentRequisition_requisitionerCode", "KELLI");
		
			recentRequisition = (RecentRequisition) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				//system.out.println("RecentRequisitionDeleteByIdTest SUCCESS");
				dbs.commit();
			}
		
			//system.out.println(incomingRequest);
			//system.out.println("RecentRequisitionDeleteByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}