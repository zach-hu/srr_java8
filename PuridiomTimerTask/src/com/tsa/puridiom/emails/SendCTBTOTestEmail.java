package com.tsa.puridiom.emails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class SendCTBTOTestEmail
{
	public StringBuffer getReplyMsg(int success, String organizationId, String fileName, String number)
	{
		StringBuffer sb = new StringBuffer();
		if(success == Status.SUCCEEDED)
		{
			sb.append(DictionaryManager.getInstance("emails", organizationId).getProperty("mail.reply.success.msg", "Email was received and attached to the form."));
			sb.append(" ");
			sb.append(number);
			sb.append(".");
		}
		else
		{
			sb.append(DictionaryManager.getInstance("emails", organizationId).getProperty("mail.reply.failure.msg", "Your email has been procesed but coulnd't find a matching form."));
			sb.append(":\n\n");
			if(fileName.indexOf(".eml") > 0)
			{
				fileName = fileName.replaceAll(".eml", "");
			}
			sb.append(fileName);
			sb.append(".");
			sb.append("\n\n");
			sb.append("Subject Format: [Document Type] [DOCUMENT_NUMBER]/[RELEASE NUMBER] TITLE");
			sb.append(".\n");

		}
		return sb;
	}

	public void sendTestEmail(String msg, String organizationId, String subject)
	{
		Log.debug(this, "sendEmail starts msg:[" + msg + "]");

		Message msgReply = new MimeMessage(this.getEmailSession(organizationId));
		String to = "procsys@ctbto.org";
		String from = "test1@puridiom.com";
		try
		{
			msgReply.setRecipients(RecipientType.TO, new Address[]{new InternetAddress(to)});
			msgReply.setFrom(new InternetAddress(from));
			msgReply.setText(msg);
			msgReply.setSubject(subject);
		}
		catch (AddressException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.sendIt(msgReply);

	}
	public void sendEmail(String msg, String organizationId)
	{
		Log.debug(this, "sendEmail starts msg:[" + msg + "]");

		Message msgReply = new MimeMessage(this.getEmailSession(organizationId));
		String to = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.bcc", "support@puridiom.com");
		String from = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.to", "support@puridiom.com");
		try {
			msgReply.setRecipients(RecipientType.TO, new Address[]{new InternetAddress(to)});
			msgReply.setFrom(new InternetAddress(from));
			msgReply.setText(msg);
			msgReply.setSubject("Puridiom Services Email Notification.");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.sendIt(msgReply);

	}
	public Session getEmailSession(String organizationId)
	{
		Session session = null;
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", EmailServerFactory.getInstance(organizationId, EmailServer.SMTP).getServer());
			props.put("mail.smtp.auth", "true");
			//props.put("mail.debug", "true");
			props.put("mail.transport.protocol", "smtp");
			EmailAuthenticator auth = new EmailAuthenticator();
			auth.setOrganizationId(organizationId);
			session = Session.getInstance(props, auth);
			session.setDebug(true);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return session;
	}
	public void buildEmailFromFile(String emailFileName, String organizationId, int success, String number)
	{
		File emailFile = new File(emailFileName);
		Log.debug(this, "buildEmailFromFile starts [" + emailFile.getName() + "][" + success + "]");

		Session session = this.getEmailSession(organizationId);
		Message msgReply = new MimeMessage(session);
		FileInputStream fis = null;
		try
		{
			fis = new FileInputStream(emailFile);
			Message msgEmail = new MimeMessage(session, fis);
			Log.debug(this, "to: " + InternetAddress.toString(msgEmail.getFrom()));
			msgReply.setRecipients(RecipientType.TO, msgEmail.getFrom());
			String bcc = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.bcc", "support@puridiom.com");
			msgReply.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			if(success == Status.SUCCEEDED)
			{
				msgReply.setSubject("Message Saved. " + msgEmail.getSubject().toUpperCase());
			}
			else
			{
				msgReply.setSubject("Saving Failure. " + msgEmail.getSubject().toUpperCase());
			}
			Log.debug(this, "subject: " + msgReply.getSubject());
			Address toAddies[] = msgEmail.getAllRecipients();
			boolean noFrom = false;
			String from = "";
			if(toAddies != null)
			{
				if(toAddies.length < 1)
				{
					noFrom = true;
				}
				else
				{
					from = ((InternetAddress)toAddies[0]).toString();
				}
			}
			else
			{
				noFrom = true;
			}
			if(noFrom)
			{
				from = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.from", "");
			}
			Log.debug(this, "from: " + from);
			msgReply.setFrom(new InternetAddress(from));
			msgReply.setText(this.getReplyMsg(success, organizationId, emailFile.getName(), number).toString());

		}
		catch (FileNotFoundException e)
		{
			Log.error(this, "buildEmailFromFile failed to find file." + emailFile.getName());
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			Log.error(this, "buildEmailFromFile couldn't send reply email." + emailFile.getName());
			e.printStackTrace();
		}
		finally
		{
			if(fis != null)
			{
				try
				{
					fis.close();
				}
				catch (IOException e)
				{
					Log.error(this, "Reply email class could not close file");
					e.printStackTrace();
				}
			}
		}
		Log.error(this, "buildEmailFromFile sending email." + emailFile.getName());
		this.sendIt(msgReply);

		Log.debug(this, "buildEmailFromFile done");
	}

	public void sendIt(Message msg)
	{
		try
		{
			Transport.send(msg);
		}
		catch (MessagingException e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		}
	}
}
