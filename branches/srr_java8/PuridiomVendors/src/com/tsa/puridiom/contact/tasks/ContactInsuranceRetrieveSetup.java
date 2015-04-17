package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class ContactInsuranceRetrieveSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String vendorId = "";

			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			if (vendor != null) {
				vendorId = vendor.getVendorId();
			} else {
				vendorId = HiltonUtility.ckNull((String)incomingRequest.get("Vendor_vendorId"));
			}

			incomingRequest.put("Contact_contactType", "INSURANCE");
			incomingRequest.put("Contact_vendorId", vendorId);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
