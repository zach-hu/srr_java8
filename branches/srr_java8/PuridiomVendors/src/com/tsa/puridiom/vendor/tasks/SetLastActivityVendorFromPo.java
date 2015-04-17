package com.tsa.puridiom.vendor.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class SetLastActivityVendorFromPo extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Vendor vendor = (Vendor)incomingRequest.get("vendor");

			if (vendor != null)
			{
				vendor.setLastActive(Dates.getDate(Dates.today("","")));
                // set to default system time zone since the vendor can be used across multiple time zones
				result = vendor;
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
			e.printStackTrace();
			throw e;
		}
		return result;
	}
}
