package com.tsa.puridiom.alerts.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.alerts.Alert;
import com.tsa.puridiom.alerts.AlertManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class AlertsExecuteAction extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");

		try
		{
			String alertName = (String)incomingRequest.get("alertname");
			Alert alert = AlertManager.getInstance().getAlert(organizationId, alertName);

			String action = alert.getProcess();
			if(!Utility.isEmpty(action))
			{
				PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
				PuridiomProcess process = processLoader.loadProcess(action);
				Map newIncomingRequest = new HashMap();
				newIncomingRequest.put("organizationId", organizationId);
				try
				{
					process.executeProcess(newIncomingRequest);
				}
				catch (Exception e) {
					// TODO: handle exception if process to be executed from alert fails.
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AlertGetProcessFromType failed!" + e.getMessage(), e);
		}
		return ret;
	}
}
