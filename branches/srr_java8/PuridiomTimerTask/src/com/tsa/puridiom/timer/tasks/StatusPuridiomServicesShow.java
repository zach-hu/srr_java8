package com.tsa.puridiom.timer.tasks;

import java.util.Map;

import com.tsa.puridiom.timer.UpdateJob;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class StatusPuridiomServicesShow extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		String oid = (String) incomingRequest.get("organizationId");

		String status = "Stopped";
		String lastRun = "Not Date";

		try
		{
			UpdateJob updateJob = new UpdateJob(oid);
			updateJob.getXmlDocument();

			if (updateJob.getRunning() != null && updateJob.getRunning().equals("Y"))
			{
				status = "Running";
			}

			if (!updateJob.getScheduleLastRun().equals(""))
			{
				lastRun = updateJob.getScheduleLastRun();
			}

			incomingRequest.put("status", status);
			incomingRequest.put("lastRun", lastRun);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
