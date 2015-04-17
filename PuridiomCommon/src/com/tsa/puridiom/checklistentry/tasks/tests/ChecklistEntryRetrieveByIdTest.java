package com.tsa.puridiom.checklistentry.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.checklistentry.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistEntryRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistEntryRetrieveById test = new ChecklistEntryRetrieveById();
			Map incomingRequest = new HashMap();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
		
			System.out.println("Database Status: " + dbs.getStatus());
		
			ChecklistEntry checklistEntry = (ChecklistEntry) test.executeTask(incomingRequest);
		
			System.out.println("ChecklistEntry: " + checklistEntry.toString());
			System.out.println("ChecklistEntryRetrieveByIdTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}