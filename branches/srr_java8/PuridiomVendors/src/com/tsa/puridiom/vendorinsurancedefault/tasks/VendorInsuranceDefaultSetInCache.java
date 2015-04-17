package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.entity.VendorInsuranceDefault;
import com.tsa.puridiom.vendorinsurancedefault.VendorInsuranceDefaultManager;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class VendorInsuranceDefaultSetInCache extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try 
		{
			String	organizationId = (String) incomingRequest.get("organizationId");
			VendorInsuranceDefault vendorInsuranceDefault = (VendorInsuranceDefault) incomingRequest.get("vendorInsuranceDefault");
			
			if (vendorInsuranceDefault != null)
			{
				VendorInsuranceDefaultManager.getInstance().setVendorInsuranceDefaultInCache(organizationId, vendorInsuranceDefault);
			}
			
			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}

		return result;
	}
}
