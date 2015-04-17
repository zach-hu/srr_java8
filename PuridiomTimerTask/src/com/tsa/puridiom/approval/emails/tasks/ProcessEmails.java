package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import com.tsa.puridiom.emails.ProcessIncomingEmails;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class ProcessEmails extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			Log.debug(this, "Starting to process emails");
			ProcessIncomingEmails processEmails = new ProcessIncomingEmails();
			processEmails.setOrganizationId((String)incomingRequest.get("organizationId"));
			processEmails.processFiles();

			this.setStatus(Status.SUCCEEDED);
			Log.debug(this, "done processing emails");
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Timer could not be setup", e);
		}
		return super.executeTask(object);
	}
}
