package com.tsa.puridiom.rfqquestion.tasks.tests;

import com.tsa.puridiom.rfqquestion.tasks.*;
import com.tsagate.foundation.database.*;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class RfqQuestionAddStandardTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("puridiom");
			RfqQuestionAddStandard test = new RfqQuestionAddStandard();
			Map incomingRequest = new HashMap();
			String	icQuestions[] = {"999999999999", "999999999901", "791091100000"};

			dbs.startTransaction();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "puridiom4");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("StdQuestion_icQuestion", icQuestions) ;
			incomingRequest.put("RfqHeader_icRfqHeader", "99999900000");
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