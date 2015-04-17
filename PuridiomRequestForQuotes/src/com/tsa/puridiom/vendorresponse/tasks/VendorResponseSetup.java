package com.tsa.puridiom.vendorresponse.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorResponseSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("VendorResponse_icRfqHeader", "0");
			incomingRequest.put("VendorResponse_icQuestion", "0");
			incomingRequest.put("VendorResponse_vendorId", "");
			incomingRequest.put("VendorResponse_response", "");
			incomingRequest.put("VendorResponse_textResponse", "");
			incomingRequest.put("VendorResponse_score", "0");

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
