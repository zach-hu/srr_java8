package com.tsa.puridiom.emails;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.emails.exception.EmailsException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class EmailManager
{
	public final static EmailManager sInstance = new EmailManager();
	private Map serverDomains = new HashMap();

	private EmailManager()
	{
		super();
		this.serverDomains.put("@mypuridiom.com", "@puridiom.com");
		this.serverDomains.put("@mytsagate.com", "@tsagate.com");
		this.serverDomains.put("@thegardenx.com", "@tsagate.com");
		this.serverDomains.put("@radiocityx.com", "@tsagate.com");
	}

	public static EmailManager getInstance()
	{
		return sInstance;
	}

	public BodyPart getFileBodyPart(String filename)
			throws javax.mail.MessagingException
	{
		BodyPart bp = new MimeBodyPart();
		DataSource ds = null;
		if(filename.toLowerCase().indexOf("http:") > -1)
		{
			try
			{
				ds = new URLDataSource(new URL(filename));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			ds = new FileDataSource(filename);
		}
		bp.setDataHandler(new DataHandler(ds));

		return bp;
	}

	public BodyPart getFileBodyPart(File file)
			throws javax.mail.MessagingException
	{
		return this.getFileBodyPart(file, "");
	}

	public BodyPart getFileBodyPart(File file, String type)
			throws javax.mail.MessagingException
	{
		BodyPart bp = new MimeBodyPart();
		bp.setDataHandler(new DataHandler(new FileDataSource(file)));

		if (type.equalsIgnoreCase("html"))
		{
			bp.setHeader("Content-Type", "text/html;charset=UTF-8");
		} else if (type.equalsIgnoreCase("text"))
		{
			bp.setHeader("Content-Type", "text/plain;charset=UTF-8");
		}

		bp.setHeader("Content-Transfer-Encoding", "quoted-printable");

		return bp;
	}

	/**
	 * Send an email with an attachemnt and body msg(html)
	 *
	 * @param fileName
	 * @param msg
	 * @param emailBody
	 */
	public Multipart addAttachemntWithMsg(Object fileName, Message msg,  Multipart mp)
	{
		try
		{
			MimeBodyPart mbp1 = new MimeBodyPart();
			//mp.addBodyPart(mbp1);
			mp = this.addAttachments(fileName, mp);
		}
		catch (Exception me)
		{
			System.out.println("error:" + me.toString());
			me.printStackTrace();
		}

		return mp;
	}

	private String addPunchLine(String pEmailBody, String contentType, String organizationId)
	{
		String punchline = "\r\n\r\n--------------------------------------------\r\n\r\n   ";
		try
		{
			if(contentType.startsWith("text/html"))
			{
				punchline = "<br><br>--------------------------------------------<br><br>&nbsp;&nbsp;&nbsp;";
			}

			punchline += PropertiesManager.getInstance(organizationId).getProperty("EMAILPROPS", "PUNCHLINE", "Powered by Puridiom - \"Strategic Solutions Simplified\"");

		} catch(Exception e)
		{
			Log.error(this, "error adding PUCHLINE");
			e.printStackTrace();
		}

		return pEmailBody + punchline;
	}

	/*
	private Multipart addPunchLine(Multipart mp, String contentType, String organizationId)
	{
			try
			{
				MimeBodyPart mbp1 = new MimeBodyPart();

				BodyPart bp = mp.getBodyPart(0);

				String content = (String) bp.getContent();

//				String punchline = DictionaryManager.getLabel("oid", "punchline", "Powered by Puridiom - \"Strategic Solutions Simplified\"");
 				String punchline = "\r\n\r\n--------------------------------------------\r\n\r\n   ";
				punchline += DictionaryManager.getLabel("oid", "punchline", "Powered by Puridiom - \"Strategic Solutions Simplified\"");;

				mbp1.setContent(punchline, contentType);
				mbp1.setDisposition(Part.INLINE);
				mp.addBodyPart(mbp1);

			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mp;
	}
	*/

	private Multipart addAttachments(Object files, Multipart mp)
	{
		if (files instanceof String)
		{
			String fileName = (String) files;
			BodyPart attachment;
			try
			{
				attachment = this.getFileBodyPart(fileName);
				attachment.setDisposition(MimeMessage.ATTACHMENT);
				File file = new File(fileName);
				if(file.getName().toLowerCase().lastIndexOf(".txt") > -1 || file.getName().toLowerCase().lastIndexOf(".html") > -1)
				{
					attachment.setDisposition(MimeMessage.INLINE);
				}

				attachment.setFileName(file.getName());
				mp.addBodyPart(attachment);
			}
			catch (MessagingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (files instanceof Object[])
		{
			Object fileNameList[] = (Object[]) files;
			for (int i = 0; i < fileNameList.length; i++)
			{
				try
				{
					if(fileNameList[i] != null)
					{
						String fileName = (String) fileNameList[i];
						BodyPart attachment = this.getFileBodyPart(fileName);
						attachment.setDisposition(MimeMessage.ATTACHMENT);
						File file = new File(fileName);
						attachment.setFileName(file.getName());

						mp.addBodyPart(attachment);
					}
				}
				catch (MessagingException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return mp;
	}

	/**
	 * @param pFromEmailAddress
	 * @param pToEmailAddress
	 * @param pCCEmailAddress
	 * @param pSubject
	 * @param pEmailBody
	 * @param pAttachment
	 * @param organizationId
	 * @throws EmailsException
	 */
	public void sendHtmlEmail(String pFromEmailAddress, String pToEmailAddress, String pCCEmailAddress, String pSubject, String pEmailBody,
			File pAttachment, String organizationId) throws EmailsException
	{
		StringBuffer logs = new StringBuffer();
		logs.append("sendhtmlemail start");
		logs.append(" pFromEmailAddress " + pFromEmailAddress);
		logs.append(" pToEmailAddress " + pToEmailAddress);
		logs.append(" pCCEmailAddress " + pCCEmailAddress);
		logs.append(" pSubject " + pSubject);
		logs.append(" pEmailBody " + pEmailBody);
		logs.append(" pAttachment " + pAttachment);
		logs.append(" organizationId " + organizationId);
		Log.debug(this, logs.toString());

		String pToEmailAddressArray[] = getEmailAddressArray(pToEmailAddress);

		try
		{
			Message msg = new MimeMessage(EmailUtilities.getEmailSession(organizationId));
			msg.setHeader("Content-Type", "text/plain;charset=UTF-8");
			msg.setHeader("Content-Transfer-Encoding", "quoted-printable");
			EmailUtilities.setHeaders(msg, pFromEmailAddress, pToEmailAddressArray, pCCEmailAddress, organizationId, "");

			msg.setSubject(EmailUtilities.setSubject(pSubject, organizationId));
			msg.setSentDate(new Date());

			Multipart multiPart = new MimeMultipart("alternative");

			// set the text part to be empty
			BodyPart txtBodyPart = new MimeBodyPart();
			String punchline = DictionaryManager.getLabel(organizationId, "punchline", "Powered by Puridiom - \"Strategic Solutions Simplified\"");
			pEmailBody = pEmailBody + "\r\n\r\n" + punchline + "\r\n";
			//pEmailBody = pEmailBody + "<br>" + punchline + "<br>";

			txtBodyPart.setContent(pEmailBody, "text/plain;charset=UTF-8");
			multiPart.addBodyPart(txtBodyPart);

			// Get the file
			String mimeType;

			if (pAttachment != null) {

				if (pAttachment.getName().endsWith(".txt")) {
					mimeType = "text";
				} else {
					mimeType = "html";
				}

				BodyPart rel_bph = getFileBodyPart(pAttachment, mimeType);
				multiPart.addBodyPart(rel_bph);
			}

			// Set the content for the message and transmit
			msg.setContent(multiPart);

			// send message
			Transport.send(msg);
		}
		catch (MessagingException anException)
		{
			Log.error(this, "Error sending Html Email: " + anException.getMessage());

			if (anException instanceof SendFailedException)
			{
				Address[] badAddresses = ((SendFailedException) anException).getInvalidAddresses();
				if ((badAddresses != null) && (badAddresses.length != 0))
				{
					throw new EmailsException("Email undeliverable to address: " + badAddresses[0]);
				}
			}

			throw new EmailsException(anException);
		}
		catch (Throwable throwable)
		{
			throw new EmailsException(throwable);
		}
	}

	public static String[] getEmailAddressArray(String toEmailAddress)
	{
		String sAddies[] = toEmailAddress.trim().split(";|,");
		Set emails = new HashSet();

		for (int i = 0; i < sAddies.length; i++)
		{
			String emailAdress = sAddies[i];

			if (!HiltonUtility.isEmpty(emailAdress))
			{
				emails.add(emailAdress.trim());
			}
		}

		return (String[]) emails.toArray(new String[emails.size()]);
	}

	public void sendErrorEmail(String message, String organizationId)
	{
		String from = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.from", "");
		String to = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.to", "");
		String subject = "PuridiomServices Error";
		try
		{
			this.sendEmail(from, to, "", subject, message, null,organizationId);
		}
		catch (EmailsException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @param pFromEmailAddress
	 * @param pToEmailAddress
	 * @param pCCEmailAddress
	 * @param pSubject
	 * @param pEmailBody
	 * @param pAttachment
	 * @param organizationId
	 * @return
	 * @throws EmailsException
	 */
	public String sendEmail(String pFromEmailAddress, String pToEmailAddress, String pCCEmailAddress, String pSubject, String pEmailBody, Object pAttachment, String organizationId) throws EmailsException
	{
		return this.sendEmail(pFromEmailAddress, pToEmailAddress, pCCEmailAddress, "", pSubject, pEmailBody, pAttachment, organizationId);
	}

	public String sendEmail(String pFromEmailAddress, String pToEmailAddress, String pCCEmailAddress, String bCCEmailAddress, String pSubject, String pEmailBody, Object pAttachment, String organizationId) throws EmailsException
	{
		return sendEmail(pFromEmailAddress, pToEmailAddress, pCCEmailAddress, bCCEmailAddress, pSubject, pEmailBody, pAttachment, organizationId, "");
	}

	/**
	 * @param pFromEmailAddress
	 * @param pToEmailAddress
	 * @param pCCEmailAddress
	 * @param bCCEmailAddress
	 * @param pSubject
	 * @param pEmailBody
	 * @param pAttachment
	 * @param organizationId
	 * @param jobType
	 * @return
	 * @throws EmailsException
	 */
	public String sendEmail(String pFromEmailAddress, String pToEmailAddress, String pCCEmailAddress, String bCCEmailAddress, String pSubject, String pEmailBody, Object pAttachment, String organizationId, String jobType) throws EmailsException
	{
		Log.debug(this, "sending email");
		StringBuffer results = new StringBuffer();

		String sAddies[] = getEmailAddressArray(pToEmailAddress);

		try
		{
			this.sendEmailTo(pFromEmailAddress, sAddies, pCCEmailAddress, bCCEmailAddress, pSubject, pEmailBody, pAttachment, organizationId, jobType);
			results.append("Email succesfully sent to: " + sAddies + "\n");
		} catch (Exception e)
		{
			results.append(e.getMessage() + "\n");
			e.printStackTrace();
		}

		Log.debug(this, "result: " + results.toString());
		return results.toString();
	}

	public void sendEmailTo(String pFromEmailAddress, String pToEmailAddress, String pCCEmailAddress, String pSubject, String pEmailBody, Object pAttachment, String organizationId) throws EmailsException
	{
		this.sendEmailTo(pFromEmailAddress, new String[] {pToEmailAddress}, pCCEmailAddress, "", pSubject, pEmailBody, pAttachment, organizationId);
	}

	public void sendEmailTo(String pFromEmailAddress, String[] pToEmailAddress, String pCCEmailAddress, String bCCEmailAddress, String pSubject, String pEmailBody, Object pAttachment, String organizationId) throws EmailsException
	{
		this.sendEmailTo(pFromEmailAddress, pToEmailAddress, pCCEmailAddress, bCCEmailAddress, pSubject, pEmailBody, pAttachment, organizationId, "");
	}

	public void sendEmailTo(String pFromEmailAddress, String[] pToEmailAddress, String pCCEmailAddress, String bCCEmailAddress, String pSubject, String pEmailBody, Object pAttachment, String organizationId, String jobType) throws EmailsException
	{
		StringBuffer logs = new StringBuffer();
		logs.append("sendemailto start");
		logs.append(" pFromEmailAddress " + pFromEmailAddress);
		logs.append(" pToEmailAddress " + pToEmailAddress);
		logs.append(" pCCEmailAddress " + pCCEmailAddress);
		logs.append(" pSubject " + pSubject);
		logs.append(" pEmailBody " + pEmailBody);
		logs.append(" pAttachment " + pAttachment);
		logs.append(" organizationId " + organizationId);
		Log.debug(this, logs.toString());

		if (HiltonUtility.isEmpty(pEmailBody) && pAttachment == null){		return;		}

		try
		{
			Message msg = new MimeMessage(EmailUtilities.getEmailSession(organizationId, jobType));
			EmailUtilities.setHeaders(msg, pFromEmailAddress, pToEmailAddress, pCCEmailAddress, bCCEmailAddress, organizationId, jobType);
			if(pSubject != null)
			{
				int index =pSubject.indexOf("$V{companyName}");
				if( index > -1)
				{
					String companyName = PropertiesManager.getInstance(organizationId).getProperty("COMPANY", "Name", "Puridiom");
					StringBuffer tmpSB = new StringBuffer(pSubject.substring(0));
					tmpSB.replace(index,	index + 15, companyName);
					pSubject = tmpSB.toString();
				}
			}

			msg.setSubject(EmailUtilities.setSubject(pSubject, organizationId));
			msg.setSentDate(new Date());
			// create the Multipart and its parts to it
			Multipart mp = new MimeMultipart();
			String contentType = "";
			if (!HiltonUtility.isEmpty(pEmailBody))
			{

				MimeBodyPart bodyTextPat = new MimeBodyPart();
				contentType = this.getContentType(pEmailBody);

				if(pSubject.indexOf("OfficeMax Order Confirmation") < 0)
				{
//					this.addPunchLine(mp, contentType, organizationId);
					pEmailBody = this.addPunchLine(pEmailBody, contentType, organizationId);
				}

				bodyTextPat.setContent(pEmailBody, contentType);
				mp.addBodyPart(bodyTextPat);
			}

			if (!Utility.isObjectEmpty(pAttachment))
			{
				this.addAttachemntWithMsg(pAttachment, msg, mp);
			}

//			if(pSubject.indexOf("OfficeMax Order Confirmation") < 0)
//			{
//				this.addPunchLine(mp, contentType, organizationId);
//			}

			// add the Multiparts to the message
			msg.setContent(mp);
			msg.saveChanges();

			String replyTo = DictionaryManager.getInstance("emails", organizationId).getProperty("approvals.replyto", "");
			if(!Utility.isEmpty(replyTo))
	  	    {
				Log.debug(this, "Reply-to: " + replyTo);
	  	    	InternetAddress replyToAddies[] = {new InternetAddress(replyTo)};
  	    		msg.setReplyTo(replyToAddies);
	  	    }

			Transport.send(msg);
/*********************************************************************************************************************************

		      String attachEmails = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.attachEmails", "");
		      String emailPath = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "");

		      if(attachEmails.equals("Y"))
		      {
		    	  OutputStream  outputStream= new FileOutputStream (emailPath+pSubject+".eml");
	    	  		msg.writeTo(outputStream);
	    	  		outputStream.close();
		      }

***********************************************************************************************************************************/
			Log.debug(this, "done no errors");
		}
		catch (MessagingException anException)
		{
			Log.error(this, "An error ocurred sending emails: " + anException.getMessage());

			if (anException instanceof SendFailedException)
			{
				Address[] badAddresses = ((SendFailedException) anException).getInvalidAddresses();
				if ((badAddresses != null) && (badAddresses.length != 0))
				{
					throw new EmailsException("Email undeliverable to address: " + badAddresses[0]);
				}
			}

			throw new EmailsException(anException);
		}
		catch (Throwable throwable)
		{
			throw new EmailsException(throwable);
		}
		finally
		{
			Log.debug(this, "email method done");
		}
	}



	public Map getServerDomains() {
		return serverDomains;
	}

	public void setServerDomains(Map serverDomains) {
		this.serverDomains = serverDomains;
	}

	private String getContentType(String text) {
		String contentType = "text/plain;charset=UTF-8";

		if(text.toLowerCase().indexOf("<html>") > -1)
		{
			contentType = "text/html;charset=UTF-8";
		}

		return contentType;
	}

}
