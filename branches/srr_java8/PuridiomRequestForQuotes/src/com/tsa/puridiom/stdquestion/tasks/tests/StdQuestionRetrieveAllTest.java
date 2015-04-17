package com.tsa.puridiom.stdquestion.tasks.tests;

import com.tsa.puridiom.entity.*;
import com.tsa.puridiom.stdquestion.tasks.*;
import com.tsagate.foundation.database.*;
import java.util.*;

public class StdQuestionRetrieveAllTest
{
	public static void  main (String[] args) throws Exception
	{
		try
		{
			DBSession dbs = new DBSession("PURIDIOM");
			StdQuestionRetrieveAll test = new StdQuestionRetrieveAll();
			Map incomingRequest = new HashMap();

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
						
			List list = (List) test.executeTask(incomingRequest);
			System.out.println(incomingRequest);

			for (int i=0; i < list.size(); i++)
			{
				StdQuestion stdQuestion = (StdQuestion) list.get(i);
				System.out.println(stdQuestion.getQuestionTitle() + " - " + stdQuestion.getQuestionText());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}