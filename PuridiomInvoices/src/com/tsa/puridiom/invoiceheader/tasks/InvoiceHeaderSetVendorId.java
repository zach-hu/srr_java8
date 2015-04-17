package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceHeaderSetVendorId extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String isValidVendor = HiltonUtility.ckNull((String) incomingRequest.get("isValidVendor"));
			if (isValidVendor.equalsIgnoreCase("FALSE"))
			{
				incomingRequest.put("InvoiceHeader_vendorId", (String) incomingRequest.get("originalVendorId"));
				incomingRequest.put("InvoiceVendor_vendorId", (String) incomingRequest.get("originalVendorId"));
			}

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