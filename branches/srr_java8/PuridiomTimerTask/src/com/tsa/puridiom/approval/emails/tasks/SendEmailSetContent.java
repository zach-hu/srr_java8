package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;
import javax.mail.Multipart;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendEmailSetContent extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			Multipart multipart = (Multipart)incomingRequest.get("multipart");
			if(multipart != null)
			{
				msg.setContent(multipart);
				msg.saveChanges();
			}


			ret = msg;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Content of the email was not found.");
		}

		return ret;
	}

}
