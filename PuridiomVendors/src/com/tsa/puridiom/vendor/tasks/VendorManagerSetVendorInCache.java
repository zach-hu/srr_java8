package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.vendor.VendorManager;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class VendorManagerSetVendorInCache extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;

		try 
		{
			Vendor vendor = (Vendor)incomingRequest.get("vendor");
			String organizationId = (String)incomingRequest.get("organizationId");

			if (vendor != null)
			{
				VendorManager.getInstance().setVendorInCache(organizationId, vendor);
			}

			this.status = Status.SUCCEEDED;
		}
		catch(Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		finally
		{
			return null;
		}
	}
}
