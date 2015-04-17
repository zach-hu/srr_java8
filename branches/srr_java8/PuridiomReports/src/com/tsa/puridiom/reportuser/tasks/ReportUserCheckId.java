package com.tsa.puridiom.reportuser.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class ReportUserCheckId extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String icReportUser = (String) incomingRequest.get("ReportUser_icReportUser");

			if(Utility.isEmpty(icReportUser))
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("Id is necessary to retrieve a ReportUser");
			}
			else
			{
		   		this.setStatus(Status.SUCCEEDED);
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}