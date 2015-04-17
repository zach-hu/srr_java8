package com.tsa.puridiom.sendqueue.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.EmailActionCodes;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.SendQueue;
import com.tsa.puridiom.entity.UserProfile;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.SendQueue;
//import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;
//import com.tsa.puridiom.usermanager.*;

public class SendQueueAddProcess extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{

		}
		catch (Exception e)
		{

		}

		return ret;
	}

    public void sendQueueAdd(String organizationId, SendQueue sendQueue, int status)
    {
    	try
		{
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess processSendQueueAdd = processLoader.loadProcess("sendqueue-add.xml");

   	    	Map sendQueueIncomingRequest = new HashMap();
  	    	sendQueueIncomingRequest.put("organizationId", organizationId);
  	    	sendQueueIncomingRequest.put("sendQueue", sendQueue);
  	    	sendQueueIncomingRequest.put("organizationId", organizationId);
  	    	sendQueueIncomingRequest.put("SendQueue_docic",sendQueue.getDocic().toString());
  	    	sendQueueIncomingRequest.put("SendQueue_action","EN");
  	    	sendQueueIncomingRequest.put("SendQueue_subject",sendQueue.getDoctype()+" "+sendQueue.getDocic().toString()+" "+ "Upload Items Confirmation");
  	    	sendQueueIncomingRequest.put("SendQueue_doctype",sendQueue.getDoctype());
  	    	sendQueueIncomingRequest.put("SendQueue_sendto",sendQueue.getSendto());
  	    	sendQueueIncomingRequest.put("SendQueue_owner",sendQueue.getOwner());

  	    	if(status==10)
  	    	{
  	    	sendQueueIncomingRequest.put("SendQueue_messagetext",
  	    			" The following items have been added to"+" "+sendQueue.getDoctype()+" "+sendQueue.getDocic().toString()+" "+"Added BY: "+sendQueue.getOwner());
  	    	}
  	    	else
  	    	{
  	    	sendQueueIncomingRequest.put("SendQueue_messagetext","Your Upload Items Process was Failed" +
  	    		" please try again or contact your System Administrator ");
      	    }

        	processSendQueueAdd.executeProcess(sendQueueIncomingRequest);
  	    	this.setStatus(processSendQueueAdd.getStatus());
         }
		 catch (Exception e)
		 {
			this.setStatus(Status.FAILED);
			try {
				 throw new TsaException("SendQueueAddProcess failed............. ");
			}  catch (TsaException e1) {
			   e1.printStackTrace();
			}
		 }
    }


}