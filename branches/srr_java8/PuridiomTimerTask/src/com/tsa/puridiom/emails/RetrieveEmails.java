/*
 * Created on Aug 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.emails;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import com.tsa.puridiom.timer.ScheduleManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.properties.DictionaryManager;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RetrieveEmails
{
  private String processXml = "Y";
  private int iNumMessagesReceived = 0;
  private int iNumMessagesProcessed = 0;
  private EmailsMisc misc = null;

  public int getNumMessagesProcessed()
  {
    return this.iNumMessagesProcessed;
  }

  public void getEmails( String organizationId)
  {
      Log.debug(this, "getEmails starts");
    Store store = null;
    Folder folder = null;
    try
    {
      Properties props = new Properties ();
      Pop3Server pop3 = new Pop3Server(organizationId);
      props.put("mail.pop3.host", pop3.getServer());
      String emaildebug = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.debug", "false");
      if(emaildebug.equalsIgnoreCase("true"))
      {
    	  props.put("mail.debug", "true");
      }
      EmailAuthenticator auth = new EmailAuthenticator();
      auth.setOrganizationId(organizationId);
      Session session = Session.getDefaultInstance(props, auth);
      store = session.getStore("pop3");
      store.connect();

      folder = store.getFolder("INBOX");
      folder.open(Folder.READ_WRITE);

      Message message[] = folder.getMessages();
      Log.debug(this,  "we have [" + String.valueOf(message.length) + "] messages");
      misc = EmailsMisc.getInstance(organizationId);
      misc.setFound(message.length);
      for (int i = 0; i < message.length; i++)
      {
    	  Log.debug(this, "\tProcessing msg [" + String.valueOf(i) +"]: " + this.messageDetails(message[i]));

			if ( this.rule(message[i], organizationId))
			{
			  this.handleMessage(message[i], organizationId);
			  misc.setProcessed(1);
			}
			if(message[i].getSubject().equalsIgnoreCase("stop"))
			{
				ScheduleManager.getInstance().stop(organizationId);
			}
			this.iNumMessagesReceived++;
      }
      Log.debug(this, "\tincoming inbox processed going to sleep");
    }
    catch (Exception e)
    {
    	Log.error(this, e.getMessage());
        e.printStackTrace();
    }
    finally
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
      }
      catch (Exception e)
      {
          Log.error(this, "error closing email inbox" + e.getMessage());
          e.printStackTrace();
      }
    }
  }

  public String messageDetails(Message message)
  {
    StringBuffer sb = new StringBuffer();
    try
    {
      sb.append("from: " + this.printfrom(message.getFrom()));
      sb.append("Subject: " + message.getSubject() +"\r\n");
      //message.writeTo(arg0)
    }
    catch (MessagingException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return sb.toString();

  }
  /**
   * rule to process email
   * @param message
   * @return
   */
  protected boolean rule(Message message, String organizationId)
  {
    boolean ret = false;
    Log.debug(this, "Message Rules. ");
    try
    {
      ret = this.checkTo(message.getAllRecipients(), organizationId);
    }
    catch (MessagingException e)
    {
      ret = false;
      e.printStackTrace();
    }
    try
    {
		if(message.getSubject().equalsIgnoreCase("stop"))
		{
			ret = false;
		}
	}
    catch (MessagingException e)
    {
		// something is wron with message
    	Log.error(this, "Message was not processed. " + this.messageDetails(message) + e.getMessage());
    	ret = false;
		e.printStackTrace();
	}
    if(!ret)
    {
    	Log.error(this, "Message was not processed.");
		SendReplyEmail errorReplyEmail = new SendReplyEmail();
		errorReplyEmail.sendEmail("Message was not processed.", organizationId);
    }
    Log.debug(this, "Message rules returns: " + ret);
    return ret;
  }

  private boolean checkTo(Address[] to, String organizationId)
  {
    String toDefault = DictionaryManager.getInstance("emails", organizationId).getProperty("mail.to", "");
    Log.debug(this, "rules checking email was sent to: " + toDefault);
    for (int j = 0; j < to.length; j++)
    {
      InternetAddress addie = (InternetAddress)to[j];
      Log.debug(this, addie.toString());
      if (addie.toString().toLowerCase().indexOf(toDefault.toLowerCase()) > -1)
      {
    	  Log.debug(this, addie.toString() + "is a good Address");
          return true;
      }
    }
    Log.debug(this, "checkTo returns false");
    return false;
  }

  /**
   * @param from
   */
  private String printfrom(Address[] from)
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
  /**
   * @param message
   */
  public void handleMessage(Message message, String organizationId)
  {
    try
    {
      String from = this.printfrom(message.getFrom());
      Object objContent = message.getContent();

      if (objContent instanceof Multipart)
      {
    	  Multipart multipart = (Multipart)message.getContent();
          //File cxml = this.handleContent(multipart);
          this.handleContent(multipart, from, message.getSubject(), organizationId);
      }
      else
      {
    	  this.handleStringContent((String)objContent, from, message.getSubject(), organizationId);
      }

      //EmailUtils.processCxml(cxml, mailProps, this.getProcessXml(), this.nowDebug);
    }
    catch (Exception e)
    {
      Log.error(this, "An error ocurred processing emails " + e.getMessage());
      e.printStackTrace();
    }
    finally
    {
      try
      {
        message.setFlag(Flags.Flag.DELETED, true);
      }
      catch (MessagingException e1)
      {
        //Log.error("Message could not be deleted", e1);
        e1.printStackTrace();
      }
    }

  }

  /**
 * @param content
 * @param from
 * @param subject
 * @param organizationId
 */
private void handleStringContent(String content, String from, String subject, String organizationId)
  {
	  try
	  {

		  WirelessEmailUtils.saveFile(subject, content, from, organizationId);


	  }
	  catch (Exception e)
	  {
		// TODO: handle exception
	  }
  }
  /**
   * @param multipart
   */
  private void handleContent(Multipart multipart, String from, String subject, String organizationId)
  {
      Log.debug(this, "handleContent");

    try
    {
      //String contentType=multipart.getContentType();
      for (int j = 0; j < multipart.getCount(); j++)
      {
        Part part = multipart.getBodyPart(j);

        String disposition = part.getDisposition();
        if (disposition != null)
        {
            InputStream in = part.getInputStream();
          if (disposition.equals(Part.ATTACHMENT))
          {
            WirelessEmailUtils.saveFile(part.getFileName(), in, from, organizationId);
          }
          else if (disposition.equals(Part.INLINE))
          {
            WirelessEmailUtils.saveFile(part.getFileName(), in, from, organizationId);
          }
          else
          {
        	  WirelessEmailUtils.saveFile(subject, in, from, organizationId);
          }
          if(in != null)
          {
              try
              {
                  in.close();
              }
              catch (IOException io)
              {
            	  Log.error(WirelessEmailUtils.class, " error Closing InputStream" + io.getMessage());
                  io.printStackTrace();
              }
          }
        }
      }
    }
    catch (Exception e)
    {
    	Log.error(WirelessEmailUtils.class, e.getMessage());
        e.printStackTrace();
    }
  }

  public static void main(String[] args)
  {
    System.out.println("getting emails");
    RetrieveEmails retrieve = new RetrieveEmails();

    retrieve.getEmails("test");

    System.out.println("\tdone getting emails");
    System.out.println("done getting emails");
  }
  public String getProcessXml()
  {
    return processXml;
  }
  public void setProcessXml(String processXml)
  {
    this.processXml = processXml;
  }
public int getINumMessagesReceived()
{
    return iNumMessagesReceived;
}
}
