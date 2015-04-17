package com.tsa.puridiom.approval.emails.tasks;

import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.properties.DictionaryManager;

public class EmailSetReplyTo extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			Message msg = (Message)incomingRequest.get("emailMessage");
			String replyTo =DictionaryManager.getInstance("emails", (String)incomingRequest.get("organizationId")).getProperty("mail.replyto", "");

	  	    if(!com.tsagate.foundation.utility.Utility.isEmpty(replyTo))
	  	    {
	  	    	InternetAddress replyToAddies[] = {new InternetAddress(replyTo)};
	  	    	try
	  	    	{
	  	    		msg.setReplyTo(replyToAddies);
	  	    	}
	  	    	catch (MessagingException  me)
	  	    	{
					Log.warn(this, "NoReply to was set. " + me.getMessage());
				}
	  	    	catch (IllegalStateException  iwe)
	  	    	{
	  	    		Log.warn(this, "NoReply to was set. " + iwe.getMessage());
				}
	  	    }
			ret = msg;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("To address was not Found.");
		}

		return ret;
	}


}
