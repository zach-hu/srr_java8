package com.tsa.puridiom.reportqueue.tasks;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class ReportQueueIdCheck extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String ReportQueue_icReportQueue = (String ) incomingRequest.get("ReportQueue_icReportQueue");

		if(Utility.isEmpty(ReportQueue_icReportQueue))
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Ic is necessary to retrieve an Report Queue");
		}
		else
		{
	   		this.setStatus(Status.SUCCEEDED);
		}

		return null;

	}
}