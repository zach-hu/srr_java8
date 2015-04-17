package com.tsa.puridiom.checklistresponse.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.checklistresponse.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistResponseRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistResponseRetrieveById test = new ChecklistResponseRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			ChecklistResponse checklistResponse = (ChecklistResponse) test.executeTask(incomingRequest);
		
			System.out.println("ChecklistResponse: " + checklistResponse.toString());
			System.out.println("ChecklistResponseRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}