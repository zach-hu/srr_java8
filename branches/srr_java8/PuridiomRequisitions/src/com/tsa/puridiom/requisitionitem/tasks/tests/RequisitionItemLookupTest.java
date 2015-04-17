package com.tsa.puridiom.requisitionitem.tasks.tests;

import com.tsa.puridiom.requisitionitem.tasks.*;
import com.tsagate.foundation.database.DBSession;
import java.util.*;

public class RequisitionItemLookupTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
		    String	organizationId = "PURIDIOM";
			DBSession dbsession = new DBSession(organizationId);
			
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("dbsession", dbsession);
			incomingRequest.put("RequisitionLine_icReqLine", "1839031300012");
			incomingRequest.put("icHeader", "000001");
			
			RequisitionItemLookup task = new RequisitionItemLookup();
			task.executeTask(incomingRequest);
			
			System.out.println("RequisitionItemLookupTest COMPLETE");
			
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}