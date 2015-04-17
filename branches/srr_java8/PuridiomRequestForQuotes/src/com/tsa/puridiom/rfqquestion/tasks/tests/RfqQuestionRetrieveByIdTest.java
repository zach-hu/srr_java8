package com.tsa.puridiom.rfqquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqquestion.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class RfqQuestionRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RfqQuestionRetrieveById test = new RfqQuestionRetrieveById();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("RfqQuestion_icRfqHeader", "99999900000");
			incomingRequest.put("RfqQuestion_icQuestion", "99999900111");
			
			RfqQuestion rfqQuestion = (RfqQuestion) test.executeTask(incomingRequest);
			System.out.println("Database Status: " + dbs.getStatus());
			
			System.out.println("RfqQuestion: " + rfqQuestion.toString());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}