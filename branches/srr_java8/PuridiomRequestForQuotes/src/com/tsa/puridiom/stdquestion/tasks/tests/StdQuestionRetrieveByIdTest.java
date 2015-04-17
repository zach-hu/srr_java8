package com.tsa.puridiom.stdquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stdquestion.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class StdQuestionRetrieveByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdQuestionRetrieveById test = new StdQuestionRetrieveById();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdQuestion_icQuestion", "999999999999");

			StdQuestion stdQuestion = (StdQuestion) test.executeTask(incomingRequest);
			System.out.println(incomingRequest);
			
			System.out.println("Title: " + stdQuestion.getQuestionTitle() + " -- Text: " + stdQuestion.getQuestionText());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}