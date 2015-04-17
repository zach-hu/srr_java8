package com.tsa.puridiom.emails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class SendReplyEmail
{
	public StringBuffer getReplyMsg(int success, String organizationId, String fileName, String number, String formType, String docTitle)
	{
		StringBuffer sb = new StringBuffer();
		String punchline = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.punchline", "powered by Puridiom \"enabling self-service procurement\"");
		if(success == Status.SUCCEEDED)
		{
			sb.append(DictionaryManager.getInstance("emails", organizationId).getProperty("mail.reply.success.msg", "Your email was received and processed:\r\n"));
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("Attached to:\t");
			sb.append(formType);
			sb.append(" ");
			sb.append(number.substring(number.indexOf(" ")+ 1));
			sb.append("\r\n");
			sb.append("Subject Title:\t");
			sb.append(docTitle);
			sb.append("\r\nDate Received:\t");
			sb.append(Dates.today("", "") + " " + Dates.getNow(null, ""));
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append(punchline);
		}
		else
		{
			sb.append(DictionaryManager.getInstance("emails", organizationId).getProperty("mail.reply.failure.msg", "Your email was received but we could not locate:"));
			sb.append("\r\n");
			if(fileName.indexOf(".eml") > 0)
			{
				fileName = fileName.replaceAll(".eml", "");
			}
			sb.append(fileName);
			//sb.append(formType + " " + number);
			sb.append("\r\n");
			sb.append("\r\n");
			sb.append("Expected Subject Format:");
			sb.append("\r\n");
			sb.append("[Document Type] [DOCUMENT_NUMBER]/[RELEASE NUMBER] TITLE.");
			sb.append("\r\n");
			sb.append("\r\n");
			  sb.append("Where:\r\n");
			  sb.append("-\tType is one of the following (uppercase):");
			  sb.append("\r\n");
			  sb.append("\t REQ, RFP, CALL-OFF CONTRACT, FRD");
			  sb.append("\r\n");
			  sb.append("-\tDocument Number is your PO, REQ, etc., number");
			  sb.append("\r\n");
			  sb.append("-\tRelase Number is optional and should be separated from Document by a \"/\"");
			  sb.append("\r\n");
			  sb.append("-\tTITLE should be a short description of the Email (60 chars max.)");


			sb.append("\r\n");
			sb.append("\r\n");
			sb.append(punchline);
		}
		return sb;
	}

	public void sendTestEmail(String msg, String organizationId, String subject)
	{
		Log.debug(this, "sendEmail starts msg:[" + msg + "]");

		Message msgReply = new MimeMessage(this.getEmailSession(organizationId));
		String to = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.bcc", "support@puridiom.com");
		String from = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.to", "support@puridiom.com");
		try
		{
			msgReply.setRecipients(RecipientType.TO, new Address[]{new InternetAddress(from)});
			msgReply.setFrom(new InternetAddress(to));
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
		String from = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.from", "support@puridiom.com");
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
			String emaildebug = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.debug", "false");
		      if(emaildebug.equalsIgnoreCase("true"))
		      {
		    	  props.put("mail.debug", "true");
		      }
			props.put("mail.transport.protocol", "smtp");
			EmailAuthenticator auth = new EmailAuthenticator();
			auth.setOrganizationId(organizationId);
			session = Session.getInstance(props, auth);
			session.setDebug(true);
		}
		catch (Exception e)
		{
			Log.error(this, "Error getting session settings: " + e.getMessage());
		}
		return session;
	}
	public void buildEmailFromFile(String emailFileName, String organizationId, int success, String number, String formType, String title)
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
			/*String from = "";
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
			}*/
			String from = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.from", "");
			Log.debug(this, "from: " + from);
			msgReply.setFrom(new InternetAddress(from));
			msgReply.setText(this.getReplyMsg(success, organizationId, emailFile.getName(), number, formType, title).toString());

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
