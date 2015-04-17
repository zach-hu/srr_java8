package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.StdQuestion;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class RfqQuestionSetValuesFromStdQuestion extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			StdQuestion stdQuestion = (StdQuestion) incomingRequest.get("stdQuestion");
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String newIcQuestion = ukg.getUniqueKey().toString();
			
			incomingRequest.put("newResponseValue_icQuestion", newIcQuestion);
			
			incomingRequest.put("RfqQuestion_icQuestion", newIcQuestion);
			incomingRequest.put("RfqQuestion_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
			incomingRequest.put("RfqQuestion_sequence", "0");
			incomingRequest.put("RfqQuestion_questionText", stdQuestion.getQuestionText());
			incomingRequest.put("RfqQuestion_responseType", stdQuestion.getResponseType());
			incomingRequest.put("RfqQuestion_ratingMethod", stdQuestion.getRatingMethod());
			incomingRequest.put("RfqQuestion_weight", String.valueOf(stdQuestion.getWeight()));
			incomingRequest.put("RfqQuestion_maxPoints", String.valueOf(stdQuestion.getMaxPoints()));
			
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