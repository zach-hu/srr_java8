package com.tsa.puridiom.emails;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

public class ReadImapInboxTest
{
	public static void main (String args[]) throws Exception
	{
	    String host = "imap.1and1.com";

	    // Get system properties
	    Properties props = System.getProperties();
	    props.put("mail.imap.host", host);

	    // Setup authentication, get session
	    EmailAuthenticator auth = new EmailAuthenticator();
	    Session session = Session.getDefaultInstance(props, auth);

	    // Get the store
	    Store store = session.getStore("imap");
	    store.connect();

	    // Get folder
	    Folder folder = store.getFolder("INBOX");
	    folder.open(Folder.READ_WRITE);

	    // Get directory
	    Message message[] = folder.getMessages();
	    for (int i=0, n=message.length; i<n; i++)
	    {
	       System.out.println(i + ": " + message[i].getFrom()[0] + "\t" + message[i].getSubject());
	       String content = message[i].getContent().toString();
	       if (content.length() > 200)
	    	   content = content.substring(0, 200);
	       System.out.print(content);
	       message[i].setFlag(Flags.Flag.DELETED, true);
	    }
	    folder.expunge();

	    // Close connection
	    folder.close(false);
	    store.close();
	    System.out.println("end");
	  }


}
