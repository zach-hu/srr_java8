/*
 * Created on Dec 23, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.vendorVendorRetrieveSetup.java
 */
 
package com.tsa.puridiom.vendor.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class VendorRetrieveSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;
			String vendorId = (String)incomingRequest.get("Vendor_vendorId");
			incomingRequest.put("Contact_vendorId", vendorId);
			incomingRequest.put("Address_addressType", vendorId);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
