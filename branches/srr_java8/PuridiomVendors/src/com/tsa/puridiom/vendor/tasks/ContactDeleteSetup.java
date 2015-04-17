/*
 * Created on Dec 30, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.vendorContactDeleteSetup.java
 */
 
package com.tsa.puridiom.vendor.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class ContactDeleteSetup extends Task
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
			incomingRequest.put("Contact_vendorId", incomingRequest.get("Vendor_vendorId"));
			
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
