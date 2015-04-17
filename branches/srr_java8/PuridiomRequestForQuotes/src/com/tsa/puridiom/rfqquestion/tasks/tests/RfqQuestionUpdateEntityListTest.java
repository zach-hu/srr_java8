package com.tsa.puridiom.rfqquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqquestion.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqQuestionUpdateEntityListTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			RfqQuestionUpdateEntityList test = new RfqQuestionUpdateEntityList();
			Map incomingRequest = new HashMap();
			
			RfqQuestion rfqQuestion = new RfqQuestion();
			RfqQuestionPK pk = new RfqQuestionPK();
			pk.setIcRfqHeader(new BigDecimal("99999900000"));
			pk.setIcQuestion(new BigDecimal("1153941301000"));
			rfqQuestion.setComp_id(pk);
			rfqQuestion.setSequence(new BigDecimal("1"));
			rfqQuestion.setQuestionText("Test 1.");
			rfqQuestion.setResponseType("Y/N");
			
			RfqQuestion rfqQuestion2 = new RfqQuestion();
			RfqQuestionPK pk2 = new RfqQuestionPK();
			pk2.setIcRfqHeader(new BigDecimal("99999900000"));
			pk2.setIcQuestion(new BigDecimal("99999900111"));
			rfqQuestion2.setComp_id(pk2);
			rfqQuestion2.setSequence(new BigDecimal("2"));
			rfqQuestion2.setQuestionText("Please enter a brief description of the industry types you service or market your products to.");
			rfqQuestion2.setResponseType("TEXT");
			
			List list = new ArrayList();
			list.add(rfqQuestion);
			list.add(rfqQuestion2);
			
			dbs.startTransaction();
			
			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqQuestionList", list);
			
			list = (List) test.executeTask(incomingRequest);
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