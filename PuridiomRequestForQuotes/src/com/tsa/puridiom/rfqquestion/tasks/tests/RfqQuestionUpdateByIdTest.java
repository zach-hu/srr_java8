package com.tsa.puridiom.rfqquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqquestion.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqQuestionUpdateByIdTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RfqQuestionUpdateById test = new RfqQuestionUpdateById();
			Map incomingRequest = new HashMap();
			
			RfqQuestion rfqQuestion = new RfqQuestion();
			RfqQuestionPK pk = new RfqQuestionPK();
			pk.setIcQuestion(new BigDecimal("99999900111"));
			pk.setIcRfqHeader(new BigDecimal("99999900000"));
			rfqQuestion.setComp_id(pk);
			rfqQuestion.setSequence(new BigDecimal("2"));
			rfqQuestion.setQuestionText("Please enter a brief description of the types of industries you service or market your products to.");
			rfqQuestion.setResponseType("TEXT");
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqQuestion", rfqQuestion);
			
			rfqQuestion = (RfqQuestion) test.executeTask(incomingRequest);
			if (dbs.getStatus() == Status.SUCCEEDED)
			{
				dbs.commit();
			}
			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}