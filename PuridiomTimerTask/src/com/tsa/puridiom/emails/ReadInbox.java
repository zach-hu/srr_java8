package com.tsa.puridiom.emails;

import java.io.File;
import java.util.Map;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.timer.ReadInboxJob;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

public class ReadInbox extends Task
{
	private ReadInboxJob readInboxJob;

	public ReadInbox(ReadInboxJob readInboxJob)
	{
		this.readInboxJob = readInboxJob;
	}

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try
		{
			String organizationId = (String) incomingRequest.get("organizationId");
			String jobType = (String) incomingRequest.get("jobType");

			this.readInbox(organizationId, jobType);

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return super.executeTask(object);
	}

	public void readInbox(String organizationId, String jobType)
	{
		Log.debug(this, "getEmails starts");
		Store store = null;
		Folder folder = null;
		try
		{
			Session session = EmailUtilities.getEmailSession(organizationId, EmailUtilities.getAuthenticationType(jobType, organizationId), jobType, EmailServer.POP3);
			store = EmailUtilities.getPop3Store(session);
			folder = EmailUtilities.getFolder(store, "INBOX");
			this.handleMessages(folder, organizationId, jobType);

			Log.debug(this, "\tincoming inbox processed going to sleep");
		} catch (Exception e)
		{
			Log.error(this, e.getMessage());
			e.printStackTrace();
		} finally
		{
			try
			{
				if (folder != null)
				{
					folder.close(true);
				}
				if (store != null)
				{
					store.close();
				}
			} catch (Exception e)
			{
				Log.error(this, "error closing email inbox" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public void handleMessages(Folder folder, String organizationId, String jobType)
	{
		Message messages[] = null;
		String sendFrom = DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".from", "support@puridiom.com");
		String sendTo = HiltonUtility.ckNull(DictionaryManager.getInstance("emails", organizationId).getProperty(jobType + ".to"));
		boolean processAllEmails = false;
		if (HiltonUtility.isEmpty(sendTo))
		{
			processAllEmails = true;
		}

		try
		{
			messages = folder.getMessages();
		} catch (MessagingException e)
		{
			e.printStackTrace();
			return;
		}
		Log.debug(this, "we have [" + String.valueOf(messages.length) + "] messages");

		for (int i = 0; i < messages.length; i++)
		{
			int succes = Status.FAILED;
			try
			{
				Address[] sendToAddress = messages[i].getRecipients(Message.RecipientType.TO);
				String sendToEmail = "";

				if (sendToAddress.length > 0)
				{
					sendToEmail = ((InternetAddress) sendToAddress[0]).getAddress();
				}

				if (sendToEmail.equalsIgnoreCase(sendTo.trim()) || processAllEmails)
				{
					Log.debug(this, "\tProcessing msg [" + String.valueOf(i) + "]: " + EmailUtilities.messageDetails(messages[i]));

					String emailFile = EmailUtilities.saveEmailContentsToDisc(messages[i], organizationId, jobType);
					Log.debug(this, "EmailFile: " + emailFile);
					if (this.getReadInboxJob() != null)
					{
						succes = this.getReadInboxJob().processEmail(new File(emailFile), messages[i].getSubject(), sendFrom, organizationId);
					}
				} else
				{
					continue;
				}
			} catch (MessagingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.debug(this, "\tProcessing done with: " + succes);
			try
			{
				messages[i].setFlag(Flags.Flag.DELETED, true);
				Log.debug(this, "Message was deleted");
			} catch (MessagingException e1)
			{
				Log.error(this, "Message could not be deleted" + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @return the readInboxJob
	 */
	public ReadInboxJob getReadInboxJob()
	{
		return readInboxJob;
	}

	/**
	 * @param readInboxJob
	 *            the readInboxJob to set
	 */
	public void setReadInboxJob(ReadInboxJob readInboxJob)
	{
		this.readInboxJob = readInboxJob;
	}

	public static void main(String[] args)
	{
		File dir = new File("C:\\HiltonProjects\\emails\\officemax\\incoming");
		File files[] = dir.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			// ReadInbox ri = new ReadInbox();
			// String tmp = ri.getContent(files[i]);
			// System.out.println(tmp.length());
		}

	}

}
