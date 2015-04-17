package com.tsa.puridiom.recentrequisition.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.recentrequisition.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class RecentRequisitionRetrieveHeaderByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RecentRequisitionRetrieveHeaderBy test = new RecentRequisitionRetrieveHeaderBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("RecentRequisition_requisitionerCode", "KELLI");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List list = (List) test.executeTask(incomingRequest);
			for (int i=0; i < list.size(); i++)
			{
				RequisitionHeader requisition = (RequisitionHeader) list.get(i);
				System.out.println("Requisition: " + requisition.toString());
			}
		
			System.out.println("RecentRequisitionRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}