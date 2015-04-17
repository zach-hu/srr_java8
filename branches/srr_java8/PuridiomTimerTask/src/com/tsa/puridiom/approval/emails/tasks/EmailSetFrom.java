package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class EmailSetFrom extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			String from = (String)incomingRequest.get("from");

			msg.setFrom(new InternetAddress(from));
			ret = msg;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("To address was not Found.");
		}

		return ret;
	}


}
