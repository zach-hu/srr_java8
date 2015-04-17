package com.tsa.puridiom.approval.emails.tasks;

import java.io.File;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class SendEmailAddAttachments extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			Multipart multipart = (Multipart)incomingRequest.get("multipart");
			Object attachments = incomingRequest.get("attachments");
			if(attachments != null)
			{
				  //	   attach the purchaseorder.xml file to the message
				  if (attachments instanceof String)
				  {
					String fileName = (String) attachments;
					BodyPart attachment;
					try
					{
						File attachmentFile = new File(fileName);
						if(attachmentFile.exists())
						{
							attachment = this.getFileBodyPart(attachmentFile, "");
							attachment.setDisposition(MimeMessage.ATTACHMENT);

							attachment.setFileName(attachmentFile.getName());
							multipart.addBodyPart(attachment);
						}
					}
					catch (MessagingException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
				  else if (attachments instanceof Object[])
				  {
					  Object fileNameList[] = (Object[]) attachments;
					  for (int i = 0; i < fileNameList.length; i++)
					  {
						 try
						{
							String fileName = (String) fileNameList[i];
							BodyPart attachment = EmailUtils.getFileBodyPart(new File(fileName), "");
							attachment.setDisposition(MimeMessage.ATTACHMENT);
							File file = new File(fileName);
							attachment.setFileName(file.getName());

							multipart.addBodyPart(attachment);

						}
						catch (MessagingException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  }
				  }
			}
			ret =  multipart;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.warn(this, "Attachments could not be added");
		}

		return ret;
	}

	 public BodyPart getFileBodyPart(File file, String type) throws javax.mail.MessagingException
	  {
	     BodyPart bp = new MimeBodyPart();
	     bp.setDataHandler(new DataHandler(new FileDataSource(file)));

	     if(type.equalsIgnoreCase("html"))
	     {
	    	 bp.setHeader("Content-Type","text/html;charset=UTF-8");
	     }
	     bp.setHeader("Content-Transfer-Encoding", "quoted-printable");

	     return bp;
	  }


}
