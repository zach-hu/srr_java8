package com.tsa.puridiom.userlog.tasks;

import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class UserLogSetPassword extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			incomingRequest.put("UserLog_status", DocumentStatus.USERLOG_INVALID_PASSWORD);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
