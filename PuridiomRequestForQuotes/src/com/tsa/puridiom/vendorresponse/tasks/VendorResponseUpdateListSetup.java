package com.tsa.puridiom.vendorresponse.tasks;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorResponseUpdateListSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String	icRfqHeader = "";
		    
		    if (incomingRequest.containsKey("VendorResponse_icRfqHeader"))
		    {
				Object icRfqHeaderObj = incomingRequest.get("VendorResponse_icRfqHeader");
				
				if (icRfqHeaderObj instanceof String[])
				{
				    String icRfqHeaderArray[] = (String[]) icRfqHeaderObj;
				    icRfqHeader = icRfqHeaderArray[0];
				}
				else if (icRfqHeaderObj instanceof String)
				{
				    icRfqHeader = (String) icRfqHeaderObj;
				}
		    }
		    incomingRequest.put("RfqHeader_icRfqHeader", icRfqHeader);

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
