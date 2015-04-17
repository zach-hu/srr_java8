package com.tsa.puridiom.alerts.tasks;

import java.util.Map;

import com.tsa.puridiom.alerts.ReadAlerts;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class Alerts extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			String alertsXml = (String)incomingRequest.get("alertsXml");
			ReadAlerts readAlerts = new ReadAlerts(alertsXml, organizationId);
			ret = readAlerts.getAlertList();

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Alerts failed to load!", e);
		}
		return ret;
	}

}
