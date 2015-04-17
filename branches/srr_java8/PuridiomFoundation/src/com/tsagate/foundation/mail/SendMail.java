/*
 * sendMail.java
 *
 * Created on August 16, 2002, 10:37 AM
 */

package com.tsagate.foundation.mail;

/**
 *
 * @author  renzo
 */

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.servlet.http.HttpServletRequest;


public class SendMail 
{
    private Message newMessage;
    private Mail mail;
    
    /** Holds value of property error. */
    private String error;
    
	/**
	 * getMail
	 * @return
	 */
	public Mail getMail()
	{
		return mail;
	}

	/**
	 * setMail
	 * @param mail
	 */
	public void setMail(Mail mail)
	{
		this.mail = mail;
	}

    /** Creates a new instance of sendMail */
    public SendMail() 
    {
        mail = new Mail();
    }
    
    public SendMail(String asSmtp, String asTo, String asFrom, java.lang.String asBCC, String asCC, java.lang.String asTitle, String asMessage) 
    {
        mail = new Mail(asSmtp, asTo, asFrom, asBCC, asCC, asTitle, asMessage);
    }
    
    public void build()
    {
        try
        {
            Properties props = new Properties ();
            props.put("mail.smtp.host", mail.getSmtp());

            Session sendMailSession = Session.getInstance(props, null);

            newMessage = new MimeMessage(sendMailSession);

            newMessage.setSubject(mail.getTitle());
            newMessage.setText(mail.getMessage());

            InternetAddress from = new InternetAddress(mail.getFrom()); 
            newMessage.setFrom(from);
            
            InternetAddress intAddr = new InternetAddress(mail.getTo());
            newMessage.addRecipient(Message.RecipientType.TO, intAddr);
            
			/*intAddr = new InternetAddress(mail.getBcc());
            newMessage.addRecipient(Message.RecipientType.BCC, intAddr);
            
			intAddr = new InternetAddress(mail.getCc());
			newMessage.addRecipient(Message.RecipientType.CC, intAddr);
			*/
        }
        catch(MessagingException me)
        {
            
        }
    }
    
    public int sendMail()
    {
        int iRet;
        try
        {
            Transport.send(newMessage);
            iRet = 1;
        }
        catch(MessagingException me)
        {
            iRet = -1;
            
            this.setError(me.toString());
        }
        return iRet;
    }
    
    public int send()
    {
        
        this.build();
        return this.sendMail();
        
    }
    
    public void setFromRequest(HttpServletRequest request)
    {
        String sName = "";
        String sValue = "";
        StringBuffer sb = new StringBuffer();
        
        for (Enumeration e = request.getParameterNames() ; e.hasMoreElements() ;) 
        {
           sName = (String)e.nextElement();
           sValue = request.getParameter(sName);
           if(sName.equalsIgnoreCase("to"))
           {
               mail.setTo(sValue);
           }
		   else if(sName.equalsIgnoreCase("bcc"))
		   {
			  mail.setBcc(sValue);
		   }
		   else if(sName.equalsIgnoreCase("cc"))
		   {
			  mail.setCc(sValue);
		   }
           else if(sName.equalsIgnoreCase("from"))
           {
               mail.setFrom(sValue);
           }
           else if(sName.equalsIgnoreCase("SUBJECT"))
           {
               mail.setTitle(sValue);
           }
           else if(sName.equalsIgnoreCase("comments"))
           {
               mail.setMessage(sValue);
           }
           else if(sName.equalsIgnoreCase("mailhost"))
           {
               mail.setSmtp(sValue);
           }
           else
           {
               sb.append(sName + ": " + sValue + "\r\n");
           }
        }
        if(sb.length() > 0)
        {
            mail.setExtras(sb.toString());
            String stemp = mail.getMessage();
            mail.setMessage(stemp + "\r\n" + sb.toString());
        }
    }
    
    /** Getter for property error.
     * @return Value of property error.
     */
    public String getError() {
        return this.error;
    }
    
    /** Setter for property error.
     * @param error New value of property error.
     */
    public void setError(String error) {
        this.error = error;
    }
    
    public int sendFromRequest(HttpServletRequest request)
    {
        this.setFromRequest(request);
        return this.send();
    }
}
