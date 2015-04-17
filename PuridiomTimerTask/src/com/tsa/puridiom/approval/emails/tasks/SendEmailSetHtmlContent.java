package com.tsa.puridiom.approval.emails.tasks;

import java.io.File;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class SendEmailSetHtmlContent extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			String pEmailBody = (String)incomingRequest.get("emailTextBody");
			File htmlContent = (File)incomingRequest.get("htmlContent");
			Multipart multiPart = new MimeMultipart("alternative");

	        //set the text part to be empty
	        BodyPart txtBodyPart = new MimeBodyPart();
	        String punchline = DictionaryManager.getLabel("oid", "punchline", "Powered by Puridiom - \"Strategic Solutions Simplified\"");
	        pEmailBody = pEmailBody + "\r\n" + punchline +"\r\n";
	        txtBodyPart.setContent(pEmailBody, "text/plain;charset=UTF-8");
	        multiPart.addBodyPart(txtBodyPart);

	        // Get the HTML file
	        BodyPart rel_bph = getFileBodyPart(htmlContent, "html");
	        multiPart.addBodyPart(rel_bph);

			ret = multiPart;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("BCC address was not Found.");
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
