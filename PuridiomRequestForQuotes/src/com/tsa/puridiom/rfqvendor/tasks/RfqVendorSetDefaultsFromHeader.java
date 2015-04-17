package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqVendorSetDefaultsFromHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			if (rfqHeader == null)
			{
				rfqHeader = new RfqHeader();
			}
			
			if (rfqHeader.getIcRfqHeader() != null)
			{
				incomingRequest.put("RfqVendor_icRfqHeader", rfqHeader.getIcRfqHeader().toString());
			}
			
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