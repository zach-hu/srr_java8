package com.tsa.puridiom.vendorinsurance.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class VendorInsuranceRetrieveByIdAlertSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			//contractsList
			List contractsList = (List) incomingRequest.get("contractsList");
			if(contractsList.size() > 0)
			{
				PoHeader poHeader = (PoHeader) contractsList.get(0);
				incomingRequest.put("VendorInsurance_contNumber", poHeader.getPoNumber());
			}

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
