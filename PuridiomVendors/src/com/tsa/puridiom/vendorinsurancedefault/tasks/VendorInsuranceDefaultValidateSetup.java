package com.tsa.puridiom.vendorinsurancedefault.tasks;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.*;

import java.util.Map;

public class VendorInsuranceDefaultValidateSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String iclLevel = "0";
			String vendorId = "";

			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if (poHeader != null) {
				iclLevel = poHeader.getIclLevel().toString();
				vendorId = poHeader.getVendorId();
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
