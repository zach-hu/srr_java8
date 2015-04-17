/**
 *
 * Created on November 21, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoice.tasks.InvoiceSetValuesFromInvoiceVendor.java
 *
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvoiceHeaderSetApReference extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			InvoiceVendor iv =(InvoiceVendor) incomingRequest.get("invoiceVendor");
			InvoiceAddress ia = (InvoiceAddress) incomingRequest.get("invoiceAddress");
			Address a = (Address) incomingRequest.get("vendorAddress");
			String organizationId = (String) incomingRequest.get("organizationId");
			String defaultVendorRemitto = PropertiesManager.getInstance(organizationId).getProperty("INVOICE OPTIONS", "VENDOR PRIMARY REMITTO", "N") ;

			if (iv != null) {
				incomingRequest.put("InvoiceHeader_apReference", iv.getApReference());
				incomingRequest.put("InvoiceHeader_specialInst", iv.getNotes());
				incomingRequest.put("InvoiceHeader_vendorAccount", iv.getVendorAccount());
			}
			if(defaultVendorRemitto.equalsIgnoreCase("Y"))
			{
				if (a != null) {
					incomingRequest.put("InvoiceHeader_vendorAddrCode", "REMITTO");
				}
			}
			else
			{
				if (ia != null) {
					incomingRequest.put("InvoiceHeader_vendorAddrCode", ia.getComp_id().getAddressCode());
				}
			}
		}
		catch (Exception e)
		{
			throw new TsaException(e.toString());
		}
		return null;
	}

}
