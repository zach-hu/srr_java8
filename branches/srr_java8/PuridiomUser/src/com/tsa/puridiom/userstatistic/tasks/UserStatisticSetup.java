package com.tsa.puridiom.userstatistic.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class UserStatisticSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("UserStatistic_userId", "");
			incomingRequest.put("UserStatistic_statType", "");
			incomingRequest.put("UserStatistic_statKey", "");
			incomingRequest.put("UserStatistic_statYear", "");
			incomingRequest.put("UserStatistic_statMonth", "");
			incomingRequest.put("UserStatistic_statCount", "0");
			incomingRequest.put("UserStatistic_statTotal", "0");

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