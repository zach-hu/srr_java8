package com.tsa.puridiom.rfqquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.rfqquestion.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqQuestionAddTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqQuestionAdd test = new RfqQuestionAdd();
			Map incomingRequest = new HashMap();

			RfqQuestion rfqQuestion = new RfqQuestion();
			RfqQuestionPK pk = new RfqQuestionPK();

			pk.setIcRfqHeader(new BigDecimal("99999900000"));
			pk.setIcQuestion(new BigDecimal("99999900111"));
			rfqQuestion.setComp_id(pk);
			rfqQuestion.setQuestionText("Do you have an specials?");
			rfqQuestion.setResponseType("Y/N");
			rfqQuestion.setSequence(new BigDecimal("2"));

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("rfqQuestion",rfqQuestion) ;

			test.executeTask(incomingRequest);

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