package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class VendorInsuranceDefaultValidateVendorSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String iclLevel = "0";
			String vendorId = "";

			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			if (vendor != null) {
				iclLevel = vendor.getIclLevel().toString();
				vendorId = vendor.getVendorId();
			}
			if (HiltonUtility.isEmpty(vendorId))
			{
				vendorId = HiltonUtility.ckNull((String)incomingRequest.get("Vendor_vendorId"));
			}

			incomingRequest.put("InsCategoryLevel_iclLevel", iclLevel);
			incomingRequest.put("VendorInsuranceDefault_vendorId", vendorId);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
