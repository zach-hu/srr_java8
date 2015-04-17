package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import com.tsa.puridiom.emails.RetrieveEmails;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class EmailsSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			Log.debug(this, "Starting to obtain emails");
			RetrieveEmails retrieve = new RetrieveEmails();
			retrieve.getEmails((String)incomingRequest.get("organizationId"));

			this.setStatus(Status.SUCCEEDED);
			Log.debug(this, "done obtaining emails");
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Timer could not be setup", e);
		}
		return super.executeTask(object);
	}

}
