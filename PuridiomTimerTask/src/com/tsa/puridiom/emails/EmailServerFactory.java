package com.tsa.puridiom.emails;

import com.tsagate.foundation.utility.Log;

public class EmailServerFactory
{
	public static EmailServer getInstance(String organizationId, String type, String jobType)
	{
		Log.debug(EmailServerFactory.class, "Getting email Server for: " + organizationId + " Type: " + type);
		EmailServer emailServer = null;

		if(type.equalsIgnoreCase(EmailServer.SMTP))
		{
			emailServer = new SmtpServer(organizationId, jobType);
		}
		else if(type.equalsIgnoreCase(EmailServer.POP3))
		{
			emailServer = new Pop3Server(organizationId, jobType);
		}

		return emailServer;
	}

	public static EmailServer getInstance(String organizationId, String type)
	{
		return EmailServerFactory.getInstance(organizationId, type, "");
	}


}
