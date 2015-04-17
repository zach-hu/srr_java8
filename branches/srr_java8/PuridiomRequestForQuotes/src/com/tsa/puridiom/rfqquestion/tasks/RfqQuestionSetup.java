package com.tsa.puridiom.rfqquestion.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.Map;

public class RfqQuestionSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("RfqQuestion_icQuestion", ukg.getUniqueKey().toString());
			incomingRequest.put("RfqQuestion_icRfqHeader", incomingRequest.get("RfqHeader_icRfqHeader"));
			incomingRequest.put("RfqQuestion_sequence", "0");
			incomingRequest.put("RfqQuestion_questionText", "");
			incomingRequest.put("RfqQuestion_responseType", "");
			incomingRequest.put("RfqQuestion_weight", "1");
			incomingRequest.put("RfqQuestion_ratingMethod", "");
			incomingRequest.put("RfqQuestion_maxPoints", "0");

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
