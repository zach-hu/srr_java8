package com.tsa.puridiom.stdquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stdquestion.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class StdQuestionUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdQuestionUpdateById test = new StdQuestionUpdateById();
			Map incomingRequest = new HashMap();

			StdQuestion stdQuestion = new StdQuestion();
			stdQuestion.setIcQuestion(new BigDecimal("999999999999"));
			stdQuestion.setQuestionText("THIS IS JUST A TEST - UPD");
			stdQuestion.setQuestionTitle("STD QUESTION TEST UPDATE");
			stdQuestion.setResponseType("Y/N");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("stdQuestion", stdQuestion);
						
			stdQuestion = (StdQuestion) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED) {
				dbs.commit();
			}
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}