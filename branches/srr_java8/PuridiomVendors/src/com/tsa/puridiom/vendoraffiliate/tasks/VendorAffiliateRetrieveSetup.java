/*
 * Created on March 25, 2004
 * @author Kelli
 * com.tsa.puridiom.tasks.vendor.VendorRetrieveSetupByContact.java
 */
 
package com.tsa.puridiom.vendoraffiliate.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.util.Map;

public class VendorAffiliateRetrieveSetup extends Task
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
			VendorAffiliate vendorAffiliate = (VendorAffiliate) incomingRequest.get("vendorAffiliate");
			String	vendorId = "";
			
			if (vendorAffiliate != null)
			{
				vendorId = vendorAffiliate.getComp_id().getAffiliateId();
			}

			incomingRequest.put("Address_addressType", vendorId);
			incomingRequest.put("Contact_vendorId", vendorId);
			incomingRequest.put("Contact_contactType", "DEFAULT");
			incomingRequest.put("VendorCommRel_vendorId", vendorId);
			
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
