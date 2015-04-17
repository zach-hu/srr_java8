package com.tsa.puridiom.checklistresponse.tasks.tests;

import com.tsa.puridiom.checklistresponse.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

public class ChecklistResponseDeleteByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			ChecklistResponseDeleteByHeader test = new ChecklistResponseDeleteByHeader();
			Map incomingRequest = new HashMap();
		
			dbs.startTransaction();
		
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "SYSADM");
			incomingRequest.put("ChecklistResponse_icHeader", "9430230200057");
		
			test.executeTask(incomingRequest);
			
			if (dbs.getStatus() == Status.SUCCEEDED) {
				System.out.println("ChecklistResponseDeleteByHeaderTest SUCCESS");
				dbs.commit();
			}
		
			System.out.println(incomingRequest);
			System.out.println("ChecklistResponseDeleteByHeaderTest COMPLETE");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}