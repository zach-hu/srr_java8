package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendEmailSetHtmlHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			msg.setHeader("Content-Type","text/html;charset=UTF-8");
	    	msg.setHeader("Content-Transfer-Encoding", "quoted-printable");

			ret = msg;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("No header were setup.");
		}

		return ret;
	}

}
