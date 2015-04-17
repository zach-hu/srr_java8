package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceHeaderSetValuesFromInvoiceVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			InvoiceVendor invoiceVendor = (InvoiceVendor) incomingRequest.get("invoiceVendor");

			if (invoiceVendor != null)
			{
				incomingRequest.put("InvoiceHeader_vendorId", HiltonUtility.ckNull(invoiceVendor.getVendorId()));
				incomingRequest.put("InvoiceHeader_vendorName", HiltonUtility.ckNull(invoiceVendor.getVendorName()));

				String specialInst = (String) incomingRequest.get("InvoiceHeader_specialInst");
				if (HiltonUtility.isEmpty(specialInst))
				{
					incomingRequest.put("InvoiceHeader_specialInst", HiltonUtility.ckNull(invoiceVendor.getNotes()));
				}
			}

			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return null;
	}
}