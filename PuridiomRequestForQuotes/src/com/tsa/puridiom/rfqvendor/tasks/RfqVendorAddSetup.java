package com.tsa.puridiom.rfqvendor.tasks;

import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqVendorAddSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	icHeader = (String)  incomingRequest.get("RfqVendor_icRfqHeader");
			String	vendorId = (String) incomingRequest.get("RfqVendor_vendorId");
			String	organizationId = (String) incomingRequest.get("organizationId");
			
			incomingRequest.put("Vendor_vendorId", vendorId);
			incomingRequest.put("RfqLine_icRfqHeader", icHeader);
			incomingRequest.put("RfqBid_icRfqHeader", icHeader);
			
			if (!incomingRequest.containsKey("RfqVendor_bidResponseCode")) {
				incomingRequest.put("RfqVendor_bidResponseCode", PropertiesManager.getInstance(organizationId).getProperty("RFQ DEFAULTS", "ResponseCode", ""));
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