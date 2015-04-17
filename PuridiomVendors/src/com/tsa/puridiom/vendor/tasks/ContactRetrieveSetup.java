/*
 * Created on Dec 26, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.vendorContactRetrieveSetup.java
 */
 
package com.tsa.puridiom.vendor.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ContactRetrieveSetup extends Task
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
			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			incomingRequest.put("Contact_vendorId", vendor.getVendorId());
			incomingRequest.put("Address_addressType", vendor.getVendorId());
			
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
