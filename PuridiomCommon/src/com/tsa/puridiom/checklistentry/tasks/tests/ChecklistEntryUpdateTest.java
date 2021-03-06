package com.tsa.puridiom.checklistentry.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.checklistentry.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistEntryUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistEntryUpdate test = new ChecklistEntryUpdate();
			Map incomingRequest = new HashMap();
		
			ChecklistEntry checklistEntry = new ChecklistEntry();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("checklistEntry", checklistEntry);
		
			checklistEntry = (ChecklistEntry) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("ChecklistEntryUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("ChecklistEntryUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}