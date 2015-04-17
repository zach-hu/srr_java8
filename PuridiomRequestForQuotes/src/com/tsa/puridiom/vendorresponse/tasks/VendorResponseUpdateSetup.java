package com.tsa.puridiom.vendorresponse.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorResponseUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String icRfqHeader = (String) incomingRequest.get("VendorResponse_icRfqHeader");
		    String icQuestion = (String) incomingRequest.get("VendorResponse_icQuestion");
		    String vendorId = (String) incomingRequest.get("VendorResponse_vendorId");
		    
		    incomingRequest.put("RfqQuestion_icRfqHeader", icRfqHeader);
		    incomingRequest.put("RfqQuestion_icQuestion", icQuestion);

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}
