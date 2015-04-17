/*
 * Created on March 25, 2004
 * @author Kelli
 * com.tsa.puridiom.vendorqualification.tasks.VendorQualificationSetup.java
 */

package com.tsa.puridiom.vendorqualification.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorQualificationSetup extends Task
{
	/**
	 * Method executeTask.
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String	vendorId = (String) incomingRequest.get("VendorRegister_vendorId");
			String	originalVendorId = vendorId;
			if (incomingRequest.containsKey("newVendorRegister_vendorId"))
			{
				vendorId = (String) incomingRequest.get("newVendorRegister_vendorId");
			}

			incomingRequest.put("Vendor_vendorId", vendorId);
			incomingRequest.put("Contact_vendorId", vendorId);
			incomingRequest.put("Contact_contactType", "DEFAULT");
			incomingRequest.put("Contact_addressCode", "DEFAULT");
			incomingRequest.put("VendorRegCommRel_vendorId", originalVendorId);
			incomingRequest.put("VendorCommRel_vendorId", vendorId);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return ret;
	}
}
