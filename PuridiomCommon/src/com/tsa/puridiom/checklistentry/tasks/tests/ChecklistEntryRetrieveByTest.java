package com.tsa.puridiom.checklistentry.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.checklistentry.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistEntryRetrieveByTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistEntryRetrieveBy test = new ChecklistEntryRetrieveBy();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			List checklistEntryList = (List) test.executeTask(incomingRequest);
			for (int i=0; i < checklistEntryList.size(); i++)
			{
				ChecklistEntry checklistEntry = (ChecklistEntry) checklistEntryList.get(i);
				System.out.println("ChecklistEntry: " + checklistEntryList.toString());
			}
		
			System.out.println("ChecklistEntryRetrieveByTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}