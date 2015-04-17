package com.tsa.puridiom.uploaditems.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class RfqReqPoOverwrite extends Task
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
			String lineName = null;
			String callDeleteProcess = null;
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));

			if (formType.equals("RFQ"))
	        {
				lineName = "RfqLine_icRfqHeader";
		    	callDeleteProcess="rfqline-delete-by-header.xml";
	        }

	        if (formType.equals("REQ"))
	        {
	        	lineName = "RequisitionLine_icReqHeader";
	        	callDeleteProcess="requisitionline-delete-by-header.xml";
	        }

	        if (formType.equals("PO"))
	        {
	        	lineName = "PoLine_icPoHeader";
	        	callDeleteProcess="poline-delete-by-header.xml";
	        }

	        incomingRequest.put(lineName,icHeaderValue);

	        PuridiomProcess  process = processLoader.loadProcess(callDeleteProcess);
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