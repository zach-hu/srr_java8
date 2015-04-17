/*
 * Created on March 23, 2004
 * @author Kelli
 * com.tsa.puridiom.tasks.contact.ContactAddressRetrieveSetup.java
 */
 
package com.tsa.puridiom.contact.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class ContactAddressRetrieveSetup extends Task
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
			Contact contact = (Contact) incomingRequest.get("contact");
			
			if (contact != null) {
				incomingRequest.put("Address_addressType", contact.getComp_id().getVendorId());
				incomingRequest.put("Address_addressCode", contact.getComp_id().getContactCode());
			}
			else if (incomingRequest.containsKey("Contact_vendorId")) {
				incomingRequest.put("Address_addressType", incomingRequest.get("Contact_vendorId"));
			}
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
