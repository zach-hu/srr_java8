package com.tsa.puridiom.taxcode.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class TaxCodeSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("TaxCode_taxCode", "");
			incomingRequest.put("TaxCode_description", "");
			incomingRequest.put("TaxCode_taxRate", "0");
			incomingRequest.put("TaxCode_dateEntered", Dates.today("", ""));
			incomingRequest.put("TaxCode_dateExpires", Dates.today("", ""));
			incomingRequest.put("TaxCode_owner", "");
			incomingRequest.put("TaxCode_status", "");

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