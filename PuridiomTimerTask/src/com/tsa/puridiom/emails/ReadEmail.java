package com.tsa.puridiom.emails;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.tsagate.foundation.utility.Log;

public class ReadEmail
{
	public static Message display(File emlFile, String organizationId) throws Exception
	{
		Log.debug(ReadEmail.class, "Reading message from file: " + emlFile.getAbsolutePath());
		Properties props = new Properties ();
		props.put("mail.smtp.host", EmailServerFactory.getInstance(organizationId, EmailServer.SMTP).getServer());
		props.put("mail.transport.protocol", "smtp");
		Session session = Session.getInstance(props, null);
		session.setDebug(false);

        InputStream source = new FileInputStream(emlFile);
        Message message = new MimeMessage(session, source);

        Log.debug(ReadEmail.class, "Subject : " + message.getSubject());
        Log.debug(ReadEmail.class, "From : " + message.getFrom()[0]);
        Log.debug(ReadEmail.class, "--------------");
        Log.debug(ReadEmail.class, "Body : " +  message.getContent());

        return message;
    }
}
