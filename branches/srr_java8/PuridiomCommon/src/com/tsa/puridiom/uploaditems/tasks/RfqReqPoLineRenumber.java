package com.tsa.puridiom.uploaditems.tasks;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;

import java.util.ArrayList;

public class RfqReqPoLineRenumber extends Task
{

    String formType=null;

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		formType = (String)incomingRequest.get("formType");
		String icHeaderValue = (String)incomingRequest.get("icHeaderValue");

		try
		{
			String callLineRenumberProcess=null;
	
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			processLoader.setApplicationName(this.getApplicationName());
	
			if (formType.equals("RFQ"))
	        {
		    	 callLineRenumberProcess="rfqline-renumber.xml";
	        }
	
	        if (formType.equals("REQ"))
	        {
	        	callLineRenumberProcess="requisitionline-renumber.xml";
	        }
	
	        if (formType.equals("PO"))
	        {
	        	callLineRenumberProcess="poline-renumber.xml";
	        }
	
			PuridiomProcess  process = processLoader.loadProcess(callLineRenumberProcess);
		    process.executeProcess(incomingRequest);
	        this.setStatus(process.getStatus());
		}
		catch (Exception e)
		{
		 this.status = Status.FAILED;
		}


		return result;
	}

}