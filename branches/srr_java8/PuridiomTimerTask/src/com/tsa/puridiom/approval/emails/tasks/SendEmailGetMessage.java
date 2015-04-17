package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.tsa.puridiom.emails.EmailServer;
import com.tsa.puridiom.emails.EmailServerFactory;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class SendEmailGetMessage extends Task
{

	/**
	 * Gets the session object and It also gets the Message in a Mime type.
	 * @return emailMessage MimeType.
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;

		try
		{
			Log.debug(this, "Getting message object");
			 Properties props = new Properties();
		     props.put("mail.smtp.host", EmailServerFactory.getInstance((String)incomingRequest.get("organizationId"), EmailServer.SMTP).getServer());
		     Session session = Session.getInstance(props, null);
		     session.setDebug(false);
		     Message msg = new MimeMessage(session);

		     this.setStatus(Status.SUCCEEDED);

		     ret = msg;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("Email Message Object could not be obtained. Check your email settings");
		}

		return ret;
	}


}
