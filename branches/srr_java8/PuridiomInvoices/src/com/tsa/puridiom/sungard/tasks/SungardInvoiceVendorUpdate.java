package com.tsa.puridiom.sungard.tasks;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class SungardInvoiceVendorUpdate extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
		    String	originalOrganizationId = (String) incomingRequest.get("organizationId");
		    
			incomingRequest.put("organizationId", "SUNGARDEAS");

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
			PuridiomProcess process = processLoader.loadProcess("sungard-invoice-vendor-update.xml");
			process.executeProcess(incomingRequest);
			
			incomingRequest.put("organizationId", originalOrganizationId);
			
			this.setStatus(process.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}