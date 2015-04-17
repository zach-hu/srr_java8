package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class EmailSetBcc extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			String bcc =DictionaryManager.getInstance("emails", (String)incomingRequest.get("organizationId")).getProperty("mail.bcc", "test1@puridiom.com");

			msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			ret = msg;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("BCC address was not Found.");
		}

		return ret;
	}


}
