package com.tsa.puridiom.vendor.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

public class VendorUpdateApReferenceSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceHeader ivh = (InvoiceHeader) incomingRequest.get("invoiceHeader");

			if (ivh != null)
			{
				String vendorId = HiltonUtility.ckNull(ivh.getVendorId());
				incomingRequest.put("Vendor_vendorId", vendorId);

				String apReference = HiltonUtility.ckNull(ivh.getApReference());
				incomingRequest.put("Vendor_apReference", apReference);
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