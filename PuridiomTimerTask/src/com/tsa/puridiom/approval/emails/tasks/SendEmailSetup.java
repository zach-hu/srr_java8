package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendEmailSetup extends Task
{

	/*
	 * This task sets initial variables to send Email.
	 * Checks if the email will use html or text format. (standard variable will be used in rules for next tasks. ("htmlemail"))
	 * Checks if object to send (SendQueue or parameters) are present.
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return super.executeTask(object);
	}


}
