/*
 * Created on March 25, 2004
 * @author Kelli
 * com.tsa.puridiom.vendorqualification.tasks.VendorAltContactQualificationSetup.java
 */
 
package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorAltContactQualificationSetup extends Task
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
			VendorRegister	vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");
			String	vendorId = (String) incomingRequest.get("Vendor_vendorId");
			
			incomingRequest.put("Address_addressType", vendorId);
			incomingRequest.put("Contact_vendorId", vendorId);
			incomingRequest.put("Contact_contactType", "ALTERNATE");
			incomingRequest.put("Contact_emailAddr", vendorRegister.getComp_id().getContactEmailAddr());
						
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
