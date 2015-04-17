/**
 * Created on Oct 27, 2005
 * @author Kelli
 * project: HiltonInvoices
 * com.tsa.puridiom.invoicevendor.tasks.InvoiceVendorGetVendorId.java
 *
 */
package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class InvoiceVendorGetVendorId extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;
		
		try
		{
			InvoiceVendor invoiceVendor =(InvoiceVendor) incomingRequest.get("invoiceVendor");

			if (invoiceVendor == null) {
			    throw new Exception ("Invoice Vendor could not be found.");
			}
			
			result = invoiceVendor.getVendorId();
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
