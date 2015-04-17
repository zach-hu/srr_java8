package com.tsa.puridiom.approval.emails.tasks;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;

public class EmailUtils
{

	public static BodyPart getFileBodyPart(File file, String type) throws javax.mail.MessagingException
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
