package com.tsa.puridiom.alerts.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class AlertsExecuteProcessMessage extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");
		String processName = (String)incomingRequest.get("processName");

		try
		{
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);

			PuridiomProcess process = processLoader.loadProcess(processName);
			incomingRequest.put("fromSystemAlerts", "Y");
			process.executeProcess(incomingRequest);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertsExecuteProcessMessage failed for process: " + processName, e);
		}
		return ret;
	}
}
