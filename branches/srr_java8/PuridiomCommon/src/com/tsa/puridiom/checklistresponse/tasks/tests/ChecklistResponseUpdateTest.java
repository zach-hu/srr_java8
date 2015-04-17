package com.tsa.puridiom.checklistresponse.tasks.tests;
import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.checklistresponse.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistResponseUpdateTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistResponseUpdate test = new ChecklistResponseUpdate();
			Map incomingRequest = new HashMap();
		
			ChecklistResponse checklistResponse = new ChecklistResponse();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("checklistResponse", checklistResponse);
		
			checklistResponse = (ChecklistResponse) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("ChecklistResponseUpdateTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("ChecklistResponseUpdateTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}