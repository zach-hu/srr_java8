/**
 *
 * Created on August 24, 2005
 * @author kathleen
 * project: HiltonInvoices
 * com.tsa.puridiom.invoice.tasks.InvoiceCreateFromOrderSetup.java
 *
 */
package com.tsa.puridiom.supplierportal.invoice.tasks;

import java.util.Map;

import com.tsa.puridiom.vendorregistration.VendorRegistrationUserManager;
import com.tsa.puridiom.vendorregistration.RegisterUser;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class InvoiceCreateFromSupplierPortalSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;


		String	userId = (String) incomingRequest.get("userId");
		String	oid = (String) incomingRequest.get("organizationId");
		RegisterUser	user = VendorRegistrationUserManager.getInstance().getUserInCache(oid, userId);

		incomingRequest.put("InvoiceHeader_vendorId", user.getVendorId());
		incomingRequest.put("InvoiceHeader_vendorName", user.getVendorName());
		incomingRequest.put("InvoiceHeader_vendorAddrCode", user.getAddressCode());
		incomingRequest.put("InvoiceVendor_vendorId", user.getVendorId());
		incomingRequest.put("InvoiceVendor_vendorName", user.getVendorName());
		incomingRequest.put("InvoiceAddress_vendorId", user.getVendorId());
		incomingRequest.put("InvoiceAddress_addressCode", user.getAddressCode());

		//incomingRequest.put("Vendor_vendorId", poHeader.getVendorId());
		//incomingRequest.put("Address_addressType", poHeader.getVendorId());
		//incomingRequest.put("Address_addressCode", poHeader.getContactAddr());

		this.setStatus(Status.SUCCEEDED);

		return null;
	}
}