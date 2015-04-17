package com.tsa.puridiom.uploaditems.tasks;

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

public class RecalculateForm extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		List listItems=new ArrayList();

		try
		{

			listItems= (List)incomingRequest.get("readRfqReqPoItems");

			String recalculateProcessName = null;
			String formType = (String)incomingRequest.get("formType");

			if((formType.equals("REQ"))|| (formType.equals("PO")))  {

				if (formType.equals("REQ"))
	  	    	{
	  	    		recalculateProcessName = "requisition-recalculate.xml";
	  	    	}
	  	    	if (formType.equals("PO"))
	  	    	{
	  	    		recalculateProcessName = "po-recalculate.xml";
	  	    	}

	  	    String organizationId = (String)incomingRequest.get("organizationId");
	  	    PuridiomProcessLoader processLoader = new PuridiomProcessLoader(organizationId);
			PuridiomProcess processRecalculate = processLoader.loadProcess(recalculateProcessName);
			processRecalculate.executeProcess(incomingRequest);
            this.setStatus(processRecalculate.getStatus());
		    }
			else{
				this.setStatus(Status.SUCCEEDED);
				}

		}

		catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			try {
				throw new TsaException("RecalculateForm failed ");
			} catch (TsaException e1) {
				e1.printStackTrace();
			}
		}
		return ret;
	}

}


