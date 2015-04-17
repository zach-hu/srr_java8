package com.tsa.puridiom.emails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

public class EmailUtilities
{
	public static Session getEmailSession(String organizationId)
	{
		String auth = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.authenticate", "false");

		return EmailUtilities.getEmailSession(organizationId, auth.equalsIgnoreCase("true"), EmailServer.SMTP);
	}

	public static StringBuffer getEmailContentAsString (Message message)
	{
		StringBuffer sb = new StringBuffer();
		RenderableMessage rm = null;
		try
		{
			rm = new RenderableMessage(message);
			sb.append(rm.getBodytext());
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb;
	}

	public static Session getEmailSession(String organizationId, String jobType)
	{
		if(!jobType.equalsIgnoreCase("") && jobType!= null)
		{
			String auth = DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".authenticate", "");
			if(HiltonUtility.isEmpty(auth))
			{
				auth = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.authenticate", "false");
			}
			return EmailUtilities.getEmailSession(organizationId, auth.equalsIgnoreCase("true"), jobType);
		}
		else
		{
			String auth = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.authenticate", "false");
			return EmailUtilities.getEmailSession(organizationId, auth.equalsIgnoreCase("true"));
		}
	}
	/**
	 * @param organizationId
	 * @param auth
	 * @return
	 */

	/*
	public static Session getEmailSession(String organizationId, boolean auth)
	{
		return EmailUtilities.getEmailSession(organizationId, auth, "");
	}
	*/
	public static Session getEmailSession(String organizationId, boolean auth)
	{
		Log.debug("EmailUtilities", "getemailsession");
		Session session = null;
		try
		{
			Properties props = new Properties();
			props.put("mail.smtp.host", EmailServerFactory.getInstance(organizationId, EmailServer.SMTP).getServer());

			props.put("mail.transport.protocol", "smtp");
			if(auth)
			{
				props.put("mail.smtp.auth", "true");
				EmailAuthenticator emailAuthenticator = new EmailAuthenticator();
				emailAuthenticator.setOrganizationId(organizationId);
				session = Session.getInstance(props, emailAuthenticator);
			}
			else
			{
				Log.debug("EmailUtilities", "no authentication");
				session = Session.getInstance(props, null);
			}
			//session.setDebug(true);
			String debug = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.debug", "false");
			session.setDebug(debug.equalsIgnoreCase("true"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		Log.debug("EmailUtilities", "we have a session");
		return session;
	}
	public static Session getEmailSession(String organizationId, boolean auth, String jobType)
	{
		return EmailUtilities.getEmailSession(organizationId, auth, jobType, EmailServer.SMTP);
	}
	public static Session getEmailSession(String organizationId, boolean auth, String jobType, String emailServerType)
	{

		if(!jobType.equalsIgnoreCase("")&& jobType != null)
		{
			Log.debug("EmailUtilities", "getemailsession");
			Session session = null;
			try
			{
				Properties props = new Properties();
				if(emailServerType.indexOf(EmailServer.SMTP) >= 0)
				{
					props.put("mail.smtp.host", EmailServerFactory.getInstance(organizationId, EmailServer.SMTP, jobType).getServer());
				}
				else if(emailServerType.indexOf(EmailServer.POP3) >= 0)
				{
					props.put("mail.pop3.host", EmailServerFactory.getInstance(organizationId, EmailServer.POP3, jobType).getServer());
				}

				props.put("mail.transport.protocol", "smtp");
				if(auth)
				{
					props.put("mail.smtp.auth", "true");
					EmailAuthenticator emailAuthenticator = new EmailAuthenticator();
					emailAuthenticator.setOrganizationId(organizationId);
					emailAuthenticator.setJobType(jobType);
					session = Session.getInstance(props, emailAuthenticator);
				}
				else
				{
					Log.debug("EmailUtilities", "no authentication");
					session = Session.getInstance(props, null);
				}
				//session.setDebug(true);
				String debug = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.debug", "false");
				session.setDebug(debug.equalsIgnoreCase("true"));
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			Log.debug("EmailUtilities", "we have a session");
			return session;

		}
		else
		{
			return EmailUtilities.getEmailSession(organizationId, auth);
		}
	}

	public static Store getPop3Store(Session session)
	{
		Store store = null;
		try
		{
			store = session.getStore("pop3");
			store.connect();
		}
		catch (NoSuchProviderException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return store;
	}

	public static Folder getFolder(Store store, String type)
	{
		Folder folder = null;
		try
		{
			folder = store.getFolder("INBOX");
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}
		try
		{
			folder.open(Folder.READ_WRITE);
		}
		catch (MessagingException e)
		{
			e.printStackTrace();
		}

		return folder;
	}

	public static String setSubject(String subject, String organizationId)
	{
		if(DictionaryManager.getInstance("emails", organizationId).getProperty("mail.testserver", "N").equalsIgnoreCase("Y"))
	    {
			String testPrefix = PropertiesManager.getInstance(organizationId).getProperty("MISC", "TestMailPrefix", "[Puridiom Test]");
			return testPrefix + " " + subject;
	    }
		else
		{
			return subject;
		}
	}


	public static boolean getAuthenticationType(String jobType, String organizationId)
	{
		boolean auth = true;
		if(!HiltonUtility.isEmpty(jobType))
		{
			auth = DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".authenticate", "false").equals("true");
		}
		else
		{
			auth = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.authenticate", "false").equals("true");
		}
		return auth;
	}
	/**
	 * @param msg
	 * @param pFromEmailAddress
	 * @param pToEmailAddress
	 * @param pCCEmailAddress
	 * @param organizationId
	 * @return
	 */
	public static Message setHeaders(Message msg, String pFromEmailAddress, String[] pToEmailAddress, String pCCEmailAddress, String organizationId, String jobType)
	{
		return EmailUtilities.setHeaders(msg, pFromEmailAddress, pToEmailAddress, pCCEmailAddress, "", organizationId, jobType);
	}

	public static Message setHeaders(Message msg, String pFromEmailAddress, String[] pToEmailAddress, String pCCEmailAddress, String bccEmailaddress, String organizationId, String jobType)
	{
		//BCC
		String bcc = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.bcc", "");
		String preventEmailSpoofingProperty = (String) PropertiesManager.getInstance(organizationId).getProperty("MAIL", "PREVENTEMAILSPOOFING", "N");
		
		if(!Utility.isEmpty(pCCEmailAddress))
		{
			bcc = bcc + pCCEmailAddress;
		}
  	    if(!Utility.isEmpty(bcc))
  	    {
  	    	try {		msg.setRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));	}
  	    	catch (AddressException e) {	e.printStackTrace();		}
  	    	catch (MessagingException e) {	e.printStackTrace();	}
  	    }
  	    //extra bcc
  	    if(!Utility.isEmpty(bccEmailaddress))
		{
			try{	msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bccEmailaddress));}
			catch (AddressException e){		e.printStackTrace();		}
			catch (MessagingException e){	e.printStackTrace();		}
		}

  	    //FROM
  	    try
  	    {
  	    	pFromEmailAddress  = EmailUtilities.checkUserEmail(organizationId, pFromEmailAddress);
  	    	pFromEmailAddress  = EmailUtilities.checkDestinyEmail(pFromEmailAddress, organizationId);
  	    	if(DictionaryManager.getInstance("emails", organizationId).getProperty("mail.testdomain", "N").equalsIgnoreCase("Y"))
  	    	{
  	    		pFromEmailAddress = pFromEmailAddress.substring(0, pFromEmailAddress.indexOf("@")) + "@farmaster.com";
  	    	}

  	    	if( preventEmailSpoofingProperty.equalsIgnoreCase("Y") )
  	    	{
  	    		InternetAddress replyToAddies[] = {new InternetAddress(EmailUtilities.checkSendFrom(pFromEmailAddress))};
  	  	    	msg.setReplyTo(replyToAddies);
  	    	}
  	    	else
  	    	{
  	    		msg.setFrom(new InternetAddress(EmailUtilities.checkSendFrom(pFromEmailAddress)));
  	    	}
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String replyTo = "";
		//REPLYTO
  	    //String replyTo = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.replyto", "");
		//reply-to is now the "from" - MSG requirement to avoid "email spoofing"
		if (preventEmailSpoofingProperty.equalsIgnoreCase("Y")) 
		{
			replyTo = PropertiesManager.getInstance(organizationId).getProperty("mail", "from", "");
			if (Utility.isEmpty(replyTo)) 
			{
				replyTo = DictionaryManager.getInstance("emails",organizationId).getProperty("mail.from", "");
			}
		}
		else 
		{
			replyTo = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.replyto", "");
		}
		
  	    if(!Utility.isEmpty(replyTo))
  	    {
  	    	 if(!Utility.isEmpty(replyTo))
  	  	    {
  	  	    	try
  	  	    	{
  	  	    		InternetAddress replyToAddies[] = {new InternetAddress(replyTo)};
  	  	    		if (preventEmailSpoofingProperty.equalsIgnoreCase("Y")) 
  	  	    		{
						msg.setFrom(new InternetAddress(replyTo));
					} 
  	  	    		else 
					{
						msg.setReplyTo(replyToAddies);
					}
  	  	    	}
  	  	    	catch (MessagingException  me)
  	  	    	{
  					// TODO: handle exception
  				}
  	  	    	catch (IllegalStateException  iwe)
  	  	    	{
  					// TODO: handle exception
  				}
  	  	    }
  	    }

  	    //TO
  	    try
  	    {

  	    	String pToSingleEmailAddress;
			String redirectTo = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.redirectto", "");

			if (jobType.equalsIgnoreCase("efax") && DictionaryManager.getInstance("emails", organizationId).getProperty("efax.testuniqueefax", "N").equalsIgnoreCase("Y"))
			{
				String propertyFax = PropertiesManager.getInstance(organizationId).getProperty("efax","@efax","@efaxsend.com");
				String propertyNumber = PropertiesManager.getInstance(organizationId).getProperty("efax","faxnumber","717-691-5690");

				String sendto = "1" + propertyNumber.replaceAll("\\W|[a-zA-Z]", "") + propertyFax;

				InternetAddress uniqueRecipient = new InternetAddress(sendto);
				msg.setRecipient(Message.RecipientType.TO, uniqueRecipient);

				Log.debug(EmailUtilities.class, " Set UNIQUE EMAIL for test to " + sendto);
			}
			else
			{
			if (DictionaryManager.getInstance("emails", organizationId).getProperty("mail.testuniqueemail", "N").equalsIgnoreCase("Y"))
			{
				String uniqueEmail = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.uniqueemail", "test1@puridiom.com");
				InternetAddress uniqueRecipient = new InternetAddress(uniqueEmail);
				msg.setRecipient(Message.RecipientType.TO, uniqueRecipient);

				Log.debug(EmailUtilities.class, " Set UNIQUE EMAIL for test to " + uniqueEmail);
			} else
			{
				InternetAddress recipients[] = new InternetAddress[pToEmailAddress.length];

				for (int i = 0; i < recipients.length; i++)
				{
					pToSingleEmailAddress = pToEmailAddress[i];
					pToSingleEmailAddress = EmailUtilities.checkUserEmail(organizationId, pToSingleEmailAddress);
					pToSingleEmailAddress = EmailUtilities.checkDestinyEmail(pToSingleEmailAddress, organizationId);

					if (DictionaryManager.getInstance("emails", organizationId).getProperty("mail.testdomain", "N").equalsIgnoreCase("Y"))
					{
						pToSingleEmailAddress = pToSingleEmailAddress.substring(0, pToSingleEmailAddress.indexOf("@")) + "@farmaster.com";
					}

					if (HiltonUtility.isEmpty(redirectTo))
					{
						if (DictionaryManager.getInstance("emails", organizationId).getProperty("mail.testdomain", "N").equalsIgnoreCase("Y"))
						{
							pToSingleEmailAddress = pToSingleEmailAddress.substring(0, pToSingleEmailAddress.indexOf("@")) + "@farmaster.com";
						}
					} else
					{
						if (pFromEmailAddress.indexOf("thegarden.com") > 0 || pFromEmailAddress.indexOf("radiocity.com") > 0)
						{
							pToSingleEmailAddress = redirectTo;
						}
					}

					recipients[i] = new InternetAddress(pToSingleEmailAddress);
				}

				msg.setRecipients(Message.RecipientType.TO, recipients);
			}
			}
		}
  	    catch (AddressException e)
  	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Log.debug(EmailUtilities.class, "done setting headers");
        return msg;

	}

	public static String checkSendFrom(String sendFrom)
	  {
		  String ret = "";
		  int index = sendFrom.indexOf(";");
		  if( index > 0)
		  {
			  ret = sendFrom.substring(0, index);
		  }
		  else
		  {
			  ret = sendFrom;
		  }

		  return ret;
	  }

	public static String checkDestinyEmail(String destinyEmail, String organizationId)
	{
		String destinyEmailTmp = destinyEmail.toLowerCase();
		String key;

		Map serverDomains = EmailManager.getInstance().getServerDomains();
		for (Iterator iter = serverDomains.keySet().iterator(); iter.hasNext();)
		{
			key = (String) iter.next();

			if (destinyEmailTmp.indexOf(key) > -1)
			{
				destinyEmail = destinyEmailTmp.replaceAll(key, (String) serverDomains.get(key));
				destinyEmail = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.redirectto", "test1@puridiom.com");
			}
		}

		return destinyEmail;
	}

	public static String checkUserEmail(String organizationId, String userEmail) throws Exception
	{
		if (userEmail.indexOf("@") <= 0)
		{
			userEmail = UserManager.getInstance().getUser(organizationId, userEmail).getMailId();
		}

		return userEmail;
	}


	/**
	 * Gets a file to save an email to.
	 * uses mail.filepath property from emails.properties
	 * <b>All emails are saved under the same directory but each "jobtype" has its own directory</b>
	 * @param organizationId
	 * @param jobType
	 * @return
	 */
	public static File getEmailFile(String organizationId, String jobType)
	{
  	  	String path = EmailUtilities.getEmailDirectory(organizationId, jobType);
		String fileExtension = DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".fileType", "txt");
		UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
		String fileName = ukg.getUniqueKey().toString() + "." + fileExtension;
		return new File(path + fileName);
	}

	public static String getEmailDirectory(String organizationId, String jobType)
	{
		String path = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "");
		return path + jobType + File.separator + "incoming" + File.separator;
	}
	public static String getContent(Message message, String jobType, String organizationId)
	  {
			RenderableMessage rm = null;
			String body = "";
			String contentType = DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".contentType", "");
			try
			{
				Log.debug(EmailUtilities.class, "EmailUtilies getContent");
				 rm = new RenderableMessage(message, contentType);
				 body = rm.getBodytext().toString();
			}
			catch (IOException e)
			{
				e.printStackTrace();
				body = "";
			}
			catch (MessagingException me)
			{
				me.printStackTrace();
				body = "";
			}
			return body;
	  }
	public static String saveEmailContentsToDisc(Message message, String organizationId, String jobType)
	{
		String emailFileName = "";

		File emailFile = EmailUtilities.getEmailFile(organizationId, jobType);
		emailFileName = emailFile.getAbsolutePath();
		String content = EmailUtilities.getContent(message, jobType, organizationId);
		EmailUtilities.saveFile(content, emailFileName, organizationId);

		return emailFileName;
	}
	public static String saveEmailToDisc(Message message, String organizationId, String jobType)
	{
		FileOutputStream fos = null;
		String emailFileName = "";
		String subject = "";
		try
		{
			subject = message.getSubject();
			File emailFile = EmailUtilities.getEmailFile(organizationId, jobType);
			emailFileName = emailFile.getAbsolutePath();
			fos = new FileOutputStream(emailFile);
			message.writeTo(fos);
		}
		catch (FileNotFoundException e)
		{
			Log.error("HandleEmails", e.getMessage() + "Email was not saved");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			Log.error("HandleEmails", e.getMessage() + "Email was not saved");
			e.printStackTrace();
		} catch (MessagingException e) {
			Log.error("HandleEmails", e.getMessage() + "Email was not saved");
			e.printStackTrace();
		}
		finally
		{
			if(fos != null)
			{
				try {
					fos.close();
				} catch (IOException e) {
					Log.error("HandleEmails", e.getMessage() + "Email was not saved to disk: " + subject );
					e.printStackTrace();
				}
			}
		}
		return emailFileName;
	}

	public static String printfrom(Address[] from)
	  {
	    StringBuffer sb = new StringBuffer();

	    for (int j = 0; j < from.length; j++)
	    {
	      //System.out.println(this.nowDebug + "\tfrom: " + from[j]);
	    	InternetAddress fromAddy = (InternetAddress)from[j];
	    	sb.append(fromAddy.getAddress());
	    	if(from.length > 1)
	    	{
	    		sb.append(";");
	    	}
	    }
	    return sb.toString();
	  }

	  public static String messageDetails(Message message)
	  {
	    StringBuffer sb = new StringBuffer();
	    try
	    {
	      sb.append("from: " + EmailUtilities.printfrom(message.getFrom()));
	      sb.append("Subject: " + message.getSubject() +"\r\n");
	    }
	    catch (MessagingException e)
	    {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	    return sb.toString();

	  }

	  public static  boolean checkTo(Address[] to, String organizationId)
	  {
	    String toDefault = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.app.to", "");
	    Log.debug("EmailUtilities", "rules checking email was sent to: " + toDefault);
	    for (int j = 0; j < to.length; j++)
	    {
	      InternetAddress addie = (InternetAddress)to[j];
	      Log.debug("EmailUtilities", addie.toString());
	      if (addie.toString().toLowerCase().indexOf(toDefault.toLowerCase()) > -1)
	      {
	    	  Log.debug("EmailUtilities", addie.toString() + "is a good Address");
	          return true;
	      }
	    }
	    Log.debug("EmailUtilities", "checkTo returns false");
	    return false;
	  }

	  public static void saveFile(String content, String fileName, String organizationId)
	    {
	        File file = new File(fileName);
	        Log.debug(EmailUtilities.class, "saving from for: " + file.getName() );
	        boolean created = false;
	        try
	        {
	            created = file.createNewFile();
	        }
	        catch (IOException e1)
	        {
	        	Log.error(WirelessEmailUtils.class, "error saving from address!" + e1.getMessage());
	            e1.printStackTrace();
	        }

	        if(created)
	        {
	            try
	            {
	            	Log.debug(EmailUtilities.class,  "saving from content " + content);
	                FileWriter fw = new FileWriter(file);
	                fw.write(content);
	                fw.close();
	            }
	            catch (FileNotFoundException e)
	            {
	            	Log.error(EmailUtilities.class,  "Response from file not found!" + e.getMessage());
	                e.printStackTrace();
	            }
	            catch (IOException e)
	            {
	            	Log.error(EmailUtilities.class,  "Error writting response xml" + e.getMessage());
	                e.printStackTrace();
	            }
	        }

	    }



}
