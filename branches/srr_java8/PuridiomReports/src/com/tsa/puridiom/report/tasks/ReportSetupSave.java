package com.tsa.puridiom.report.tasks;

import java.util.Map;

import com.tsa.puridiom.jasperreports.ReportUtils;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReportSetupSave extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
            incomingRequest.put("execute", "N");

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Report could not be saved!");
		}
		return super.executeTask(object);
	}


}
