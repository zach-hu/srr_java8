package com.tsa.puridiom.stdquestion.tasks;

import com.tsa.puridiom.entity.StdQuestion;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class StdQuestionRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    StdQuestion	stdQuestion = (StdQuestion) incomingRequest.get("stdQuestion");
			String	icQuestion = stdQuestion.getIcQuestion().toString();

			incomingRequest.put("ResponseValue_icQuestion", icQuestion);
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}