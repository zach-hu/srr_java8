package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.*;

public class RfqQuestionSaveasList extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqQuestionList */
			RfqQuestionPK comp_id = new RfqQuestionPK();
			List	rfqQuestionList = (List) incomingRequest.get("rfqQuestionList");
			
			for (int i=0; i < rfqQuestionList.size(); i++)
			{
				incomingRequest.put("rfqQuestion", (RfqQuestion) rfqQuestionList.get(i));
				
	            PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
	            PuridiomProcess process = processLoader.loadProcess("rfqquestion-saveas-by-id.xml");
	            process.executeProcess(incomingRequest);
	
	            /*
				RfqQuestionSaveas saveasTask = new RfqQuestionSaveas();
				RfqQuestion	rfqQuestion = (RfqQuestion) saveasTask.executeTask(incomingRequest);
				
				if (saveasTask.getStatus() != Status.SUCCEEDED)
				{
					throw new Exception("RfqQuestionSaveas failed.");
				}
				*/
	            
	            RfqQuestion	rfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion");
				rfqQuestionList.set(i, rfqQuestion);
			}
			
			result = rfqQuestionList;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}