package com.tsa.puridiom.stdquestion.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class StdQuestionSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("StdQuestion_icQuestion", ukg.getUniqueKey().toString());
			if (!incomingRequest.containsKey("StdQuestion_questionTitle")) {
			    incomingRequest.put("StdQuestion_questionTitle", "");
			}
			if (!incomingRequest.containsKey("StdQuestion_questionText")) {
			    incomingRequest.put("StdQuestion_questionText", "");
			}
			if (!incomingRequest.containsKey("StdQuestion_responseType")) {
			    incomingRequest.put("StdQuestion_responseType", "");
			}
			if (!incomingRequest.containsKey("StdQuestion_ratingMethod")) {
			    incomingRequest.put("StdQuestion_ratingMethod", "");
			}
			if (!incomingRequest.containsKey("StdQuestion_weight")) {
			    incomingRequest.put("StdQuestion_weight", "1");
			}
			if (!incomingRequest.containsKey("StdQuestion_maxPoints")) {
			    incomingRequest.put("StdQuestion_maxPoints", "0");
			}

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
