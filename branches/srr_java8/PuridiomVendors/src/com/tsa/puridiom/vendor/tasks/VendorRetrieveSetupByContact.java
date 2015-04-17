/*
 * Created on March 25, 2004
 * @author Kelli
 * com.tsa.puridiom.tasks.vendor.VendorRetrieveSetupByContact.java
 */
 
package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class VendorRetrieveSetupByContact extends Task
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
			Contact contact = (Contact) incomingRequest.get("contact");
			String	vendorId = "";
			
			if (contact != null)
			{
				vendorId = contact.getComp_id().getVendorId();
			}
							
			incomingRequest.put("Vendor_vendorId", vendorId);
			incomingRequest.put("Address_addressType", vendorId);
			incomingRequest.put("VendorCommRel_vendorId", vendorId);
			// for alternate contacts
			incomingRequest.put("Contact_vendorId", vendorId);
			incomingRequest.put("Contact_contactType", "ALTERNATE");
			//incomingRequest.put("Contact_contactCode", "002");
			
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
