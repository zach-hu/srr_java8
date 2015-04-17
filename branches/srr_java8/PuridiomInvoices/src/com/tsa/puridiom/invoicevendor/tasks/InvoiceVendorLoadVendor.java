/**
 *
 * Created on Auust 24, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoicevendor.tasks.InvoiceVendorLoadOrderSupplier.java
 *
 */
package com.tsa.puridiom.invoicevendor.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceVendorLoadVendor extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			Vendor vendor =(Vendor) incomingRequest.get("vendor");

			if (vendor != null) {
				incomingRequest.put("InvoiceVendor_apReference", vendor.getApReference());
			}
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return null;
	}

}
