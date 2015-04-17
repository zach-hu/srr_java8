package com.tsa.puridiom.rfqquestion.tasks.tests;

import com.tsa.puridiom.rfqquestion.tasks.*;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqQuestionDeleteByHeaderTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			String	o = "PURIDIOM";
			RfqQuestionDeleteByHeader deleteTask = new RfqQuestionDeleteByHeader();
			Map incomingRequest = new HashMap();
			DBSession dbs = new DBSession(o);
			
			dbs.startTransaction();
			
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("organizationId", o);
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("RfqQuestion_icRfqHeader", "756864200000");
			
			deleteTask.executeTask(incomingRequest);
			
			if (dbs.getStatus() == Status.SUCCEEDED)
			{
				dbs.commit();				
			}
			
			System.out.println("STATUS: " + String.valueOf(dbs.getStatus()));
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}