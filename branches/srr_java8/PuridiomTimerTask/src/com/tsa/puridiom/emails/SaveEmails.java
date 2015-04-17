package com.tsa.puridiom.emails;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;

import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class SaveEmails extends RetrieveEmails
{

	public void handleMessage(Message message, String organizationId)
	{
		String subject  = "email";
		try
		{
			subject = message.getSubject();
			Log.debug(this, "SaveEmails|Saving: " + subject);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
	    {
	      try
	      {
	    	  Log.debug(this, "deleting email");
	        message.setFlag(Flags.Flag.DELETED, true);
	        //if having problems deleting emails:
	        //folder.setFlags(msgNum+1, msgNum+1, new Flags(Flags.Flag.DELETED), true);

	      }
	      catch (MessagingException e1)
	      {
	        Log.error(this, e1.getMessage() + " Occured trying to delete email. " + subject);
	        e1.printStackTrace();
	      }
	    }
		String path = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.filepath", "c:\\temp\\") + "incoming" + File.separator;
		if(subject.indexOf("/") > 0 || subject.indexOf("\\") > 0)
		{
			String defaultReleaseSeparator = DictionaryManager.getInstance("emails", organizationId).getProperty("number.default.releaseSeparator", "-");
			if(subject.indexOf("/") > 0)
			{
				subject = subject.replaceAll("/", defaultReleaseSeparator);
			}
			if(subject.indexOf("\\") > 0)
			{
				subject = subject.replaceAll("\\", defaultReleaseSeparator);
			}
		}
		subject = subject.replaceAll(":", "");
		String mailExtension = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.extension", ".eml");
		String tmpSubject = subject;
		subject = path + subject + mailExtension;

		File emlFile = new File(subject);
		if(emlFile.exists())
		{
			String stamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
			subject = path + stamp + tmpSubject + mailExtension;
			emlFile = new File(subject);
		}
		Log.debug(this, "saving to: " + emlFile.getAbsolutePath());
		this.writeMe(message, emlFile);
		//make a backup of all incoming emails
		String bakSubject = path + "bak" + File.separator + tmpSubject + mailExtension;
		emlFile = new File(bakSubject);
		this.writeMe(message, emlFile);
	}

	public void writeMe(Message message, File fileToWrite)
	{
		FileOutputStream fos = null;
		try
		{
			fos = new FileOutputStream(fileToWrite);
			message.writeTo(fos);
		} catch (FileNotFoundException e) {
			Log.error(this, e.getMessage() + "Email was not saved");
			e.printStackTrace();
		} catch (IOException e) {
			Log.error(this, e.getMessage() + "Email was not saved");
			e.printStackTrace();
		} catch (MessagingException e) {
			Log.error(this, e.getMessage() + "Email was not saved");
			e.printStackTrace();
		}
		finally
		{
			if(fos != null)
			{
				try {
					fos.close();
				} catch (IOException e) {
					Log.error(this, e.getMessage() + "Email was not saved to disk: " + fileToWrite.getAbsolutePath());
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.println("getting emails");
	    SaveEmails retrieve = new SaveEmails();

	    retrieve.getEmails("test");

	    System.out.println("\tdone getting emails");

	}

	protected boolean rule(Message message, String organizationId) {
		Log.debug(this, "emails rules. Always true for ctbto");
		return true;
	}
}
