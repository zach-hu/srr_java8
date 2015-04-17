package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class SendEmailSetTextContent extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String emailTextBody = (String)incomingRequest.get("emailTextBody");
			Object attachments = incomingRequest.get("attachments");
			if(emailTextBody == null) { emailTextBody = ""; }
			if(attachments != null)
			{
				MimeBodyPart mbp1 = new MimeBodyPart();
		        mbp1.setContent(emailTextBody, "text/plain;charset=UTF-8");

		        //   create the Multipart and its parts to it
		        Multipart mp = new MimeMultipart();
		        mp.addBodyPart(mbp1);
		        ret = mp;
			}
			else
			{
				Message msg = (Message)incomingRequest.get("emailMessage");
				msg.setText(emailTextBody);
				incomingRequest.put("emailMessage", msg);
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("BCC address was not Found.");
		}

		return ret;
	}

}
