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
import com.tsagate.foundation.utility.Log;
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

public class SendQueueRfqReqPoAddItems extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
            String organizationId = (String)incomingRequest.get("organizationId");
            PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
            PuridiomProcess processRfqReqPoAddItems = processLoader.loadProcess("rfq-req-po-add-items.xml");

            String formIcHeader=null;

			   ArrayList listUpload = (ArrayList) incomingRequest.get("sendqueueList");

			   if (!listUpload.isEmpty())
               {

	      	    for (int i=0; i<listUpload.size();i++)
	            {
	      	    	Map newIncomingRequest = new HashMap();

	      	    	SendQueue sendQueue = (SendQueue)listUpload.get(i);

	      	    	String fileName = sendQueue.getSubject();
	      	    	String	ext = fileName.substring(fileName.lastIndexOf("."));
	      	    	int pos= fileName.lastIndexOf(".");
	      	    	String FilenameXls = fileName.substring(0,pos);


	      	    	String formType = sendQueue.getDoctype();
	      	    	String icHeader = sendQueue.getDocic().toString();

	      	    	Log.debug(this, "formType = "+ formType);
	      	    	Log.debug(this, "icHeader = "+ icHeader);

	      	    	if (formType.equals("RFQ"))
	      	    	{
	      	    	  formIcHeader = "RfqHeader_icRfqHeader";

	      	    	}
	      	    	if (formType.equals("REQ"))
	      	    	{
	      	    		formIcHeader = "RequisitionHeader_icReqHeader";
	      	    	}
	      	    	if (formType.equals("PO"))
	      	    	{
	      	    		formIcHeader = "PoHeader_icPoHeader";
	       	    	}

	    			newIncomingRequest.put("organizationId",organizationId);
	    			newIncomingRequest.put("FilenameXls",FilenameXls);
	    			newIncomingRequest.put("formType",formType);
	    			newIncomingRequest.put(formIcHeader,icHeader);

	    			processRfqReqPoAddItems.executeProcess(newIncomingRequest);

	    			if(processRfqReqPoAddItems.getStatus() == Status.SUCCEEDED)
	    			{
	    				sendQueue.setStatus("10");
                    }

	    			else
	    			{
	    				sendQueue.setStatus("99");
	    			}

	    			SendQueueUpdateXlsProcess sendQueueUpdateProcess = new SendQueueUpdateXlsProcess();
    				SendQueueAddProcess sendQueueAddProcess = new SendQueueAddProcess();
    				sendQueueUpdateProcess.sendQueueUpdate(organizationId, sendQueue);
    				sendQueueAddProcess.sendQueueAdd(organizationId,sendQueue, Integer.parseInt(sendQueue.getStatus()));

	      	  }


	      	  this.setStatus(Status.SUCCEEDED);
              }

			   else{this.setStatus(Status.SUCCEEDED);}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("SendQueueRfqReqPoAddItems failed Update Items by Xls");
		}

		return ret;
	}
}