package com.tsa.puridiom.rfqvendor.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class RfqVendorListFilter extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			List rfqVendorList = (List)incomingRequest.get("rfqVendorList");
			List rfqVendorListFilter = new ArrayList();

			if (rfqVendorList != null)
			{
				for(int i=0; i<rfqVendorList.size(); i++)
				{
					RfqVendor rfqVendor = (RfqVendor)rfqVendorList.get(i);
					if(!HiltonUtility.isEmpty(rfqVendor.getBidResponseCode()))
						rfqVendorListFilter.add(rfqVendor);
			  	}
			}
			this.setStatus(Status.SUCCEEDED);
			result = rfqVendorListFilter;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}