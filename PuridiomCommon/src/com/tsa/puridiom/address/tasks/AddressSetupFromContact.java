/*
 * Created on Dec 30, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.addressAddressSetupFromContact.java
 */

package com.tsa.puridiom.address.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class AddressSetupFromContact extends Task
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
			incomingRequest.put("Address_addressType", incomingRequest.get("Contact_vendorId"));
			incomingRequest.put("Address_addressCode", incomingRequest.get("Contact_contactCode"));

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		finally
		{
			return ret;
		}
	}
}
