package com.tsa.puridiom.checklistresponse.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.checklistresponse.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistResponseRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistResponseRetrieveBy test = new ChecklistResponseRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List checklistResponseList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < checklistResponseList.size(); i++)
			{
				ChecklistResponse checklistResponse = (ChecklistResponse) checklistResponseList.get(i);
				System.out.println("ChecklistResponse: " + checklistResponseList.toString());
			}
		
			System.out.println("ChecklistResponseRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}