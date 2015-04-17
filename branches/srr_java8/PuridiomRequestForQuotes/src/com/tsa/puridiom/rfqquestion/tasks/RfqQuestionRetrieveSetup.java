package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.RfqQuestion;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class RfqQuestionRetrieveSetup extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			RfqQuestion	rfqQuestion = (RfqQuestion) incomingRequest.get("rfqQuestion");
			String	icQuestion = rfqQuestion.getComp_id().getIcQuestion().toString();

			incomingRequest.put("ResponseValue_icQuestion", icQuestion);
			incomingRequest.put("VendorResponse_icQuestion", icQuestion);
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