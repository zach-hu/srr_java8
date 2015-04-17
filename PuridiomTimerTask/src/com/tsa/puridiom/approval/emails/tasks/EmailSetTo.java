package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class EmailSetTo extends Task
{

	/*
	 * This task sets initial variables to send Email.
	 * Checks if the email will use html or text format. (standard variable will be used in rules for next tasks. ("htmlemail"))
	 * Checks if object to send (SendQueue or parameters) are present.
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			String to = (String)incomingRequest.get("to");
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
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
