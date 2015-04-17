/*
 * Created on August 18, 2005
 */
package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsagate.foundation.processengine.*;
import java.util.*;

/**
 * @author kat
 */
public class InvoiceVendorRetrieveSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {
		Map incomingRequest = (Map) object;

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			if (invoiceHeader == null)
			{
				throw new Exception("The InvoiceHeader entity was not found.");
			}
			String vendorId = invoiceHeader.getVendorId();
			String addressCode = invoiceHeader.getVendorAddrCode();

			incomingRequest.put("InvoiceVendor_vendorId", vendorId);
			incomingRequest.put("InvoiceAddress_vendorId", vendorId);
			incomingRequest.put("InvoiceAddress_addressCode", addressCode);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}
