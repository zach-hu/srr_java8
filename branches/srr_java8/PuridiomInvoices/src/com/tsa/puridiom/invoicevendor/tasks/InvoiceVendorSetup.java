package com.tsa.puridiom.invoicevendor.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvoiceVendorSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			//incomingRequest.put("InvoiceVendor_vendorId", "");
			//incomingRequest.put("InvoiceVendor_vendorName", "");
			incomingRequest.put("InvoiceVendor_fobCode", "");
			incomingRequest.put("InvoiceVendor_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvoiceVendor_status", "02");
			incomingRequest.put("InvoiceVendor_ownerEmail", "");
			incomingRequest.put("InvoiceVendor_notes", "");

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