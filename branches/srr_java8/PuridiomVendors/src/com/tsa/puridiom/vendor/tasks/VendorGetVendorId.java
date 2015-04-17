package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class VendorGetVendorId extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Vendor vendor = (Vendor) incomingRequest.get("vendor");
			if (vendor == null)
			{
				vendor = new Vendor();
			}

			result = vendor.getVendorId();
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}