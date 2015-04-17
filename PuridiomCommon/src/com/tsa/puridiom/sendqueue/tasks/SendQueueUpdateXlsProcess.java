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
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.properties.DictionaryManager;

public class SendQueueUpdateXlsProcess extends Task
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


	public void sendQueueUpdate(String organizationId, SendQueue sendQueue)
	{
     	try
		{
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess processSendQueueUpdate = processLoader.loadProcess("sendqueue-update.xml");



	      	    	Map sendQueueIncomingRequest = new HashMap();
	      	    	sendQueueIncomingRequest.put("organizationId", organizationId);
	      	    	sendQueueIncomingRequest.put("sendQueue", sendQueue);
	      	    	processSendQueueUpdate.executeProcess(sendQueueIncomingRequest);
	      	    	this.setStatus(processSendQueueUpdate.getStatus());

		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			try {
				throw new TsaException("SendQueueUpdateByList failed SendQueue Update ");
			} catch (TsaException e1) {
				e1.printStackTrace();
			}
		}

	}

}


