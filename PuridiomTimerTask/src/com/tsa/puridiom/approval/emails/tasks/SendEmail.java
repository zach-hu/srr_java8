package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.Transport;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendEmail extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			Transport.send(msg);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			if (e instanceof SendFailedException)
			{
				Address[] badAddresses = ((SendFailedException) e).getInvalidAddresses();
				if ((badAddresses != null) && (badAddresses.length != 0))
				{
					throw new TsaException("Email undeliverable to address: " + badAddresses[0]);
				}
			}
			else
			{
				throw new TsaException("Message was not sent");
			}
		}

		return ret;
	}

}
