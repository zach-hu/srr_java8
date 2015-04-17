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
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class InvoiceVendorLoadOrderSupplier extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	organizationId = (String) incomingRequest.get("organizationId");
        String userTimeZone = (String) incomingRequest.get("userTimeZone");
        String userDateFormat = (String) incomingRequest.get("userDateFormat");

		try
		{
			Vendor vendor =(Vendor) incomingRequest.get("vendor");
			Address vendorAddress = (Address) incomingRequest.get("vendorAddress");
			//Log.debug(vendor, "Vendor = " + vendor);
			//Log.debug(vendorAddress, "VendorAddress = " + vendorAddress);

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }
            String today = Dates.today(userDateFormat, userTimeZone);

			if (vendor != null) {
				incomingRequest.put("InvoiceVendor_vendorId", vendor.getVendorId());
				incomingRequest.put("InvoiceVendor_vendorName", vendor.getVendorName());
				incomingRequest.put("InvoiceVendor_fobCode", vendor.getFobId());
				incomingRequest.put("InvoiceVendor_dateEntered", today);
				incomingRequest.put("InvoiceVendor_status", vendor.getStatus());
				incomingRequest.put("InvoiceVendor_ownerEmail", vendor.getEmailAddress());
				incomingRequest.put("InvoiceVendor_notes", vendor.getNotes());
				incomingRequest.put("InvoiceVendor_apReference", vendor.getApReference());
				incomingRequest.put("InvoiceVendor_vendorAccount", vendor.getVendorAccount());
				incomingRequest.put("InvoiceVendor_eftAccountNumber", vendor.getEftAccountNumber());
				incomingRequest.put("InvoiceVendor_eftAccountType", vendor.getEftAccountType());
				incomingRequest.put("InvoiceVendor_eftBankName", vendor.getEftBankName());
				incomingRequest.put("InvoiceVendor_eftPersonName", vendor.getEftPersonName());
				incomingRequest.put("InvoiceVendor_eftRoutingNumber", vendor.getEftRoutingNumber());
				incomingRequest.put("InvoiceVendor_eftWireAccount", vendor.getEftWireAccount());

				incomingRequest.put("InvoiceAddress_vendorId", vendor.getVendorId());
				Log.debug(vendor, "InvoiceAddress_vendorId = " + vendor.getVendorId());
				incomingRequest.put("InvoiceAddress_dateEntered", today);
				if (vendor.getDateExpires() != null) {
					incomingRequest.put("InvoiceAddress_dateExpires", vendor.getDateExpires().toString());
				}
				incomingRequest.put("InvoiceAddress_status", vendor.getStatus());
				incomingRequest.put("InvoiceAddress_owner", vendor.getOwner());
			}
			if (vendorAddress != null) {
				if (vendorAddress.getComp_id() != null) {
					incomingRequest.put("InvoiceHeader_vendorAddrCode", vendorAddress.getComp_id().getAddressCode());
					incomingRequest.put("InvoiceAddress_addressCode", vendorAddress.getComp_id().getAddressCode());
					Log.debug(vendorAddress, "InvoiceAddress_addressCode = "+vendorAddress.getComp_id().getAddressCode());
				}
				incomingRequest.put("InvoiceAddress_addressLine1", vendorAddress.getAddressLine1());
				incomingRequest.put("InvoiceAddress_addressLine2", vendorAddress.getAddressLine2());
				incomingRequest.put("InvoiceAddress_addressLine3", vendorAddress.getAddressLine3());
				incomingRequest.put("InvoiceAddress_addressLine4", vendorAddress.getAddressLine4());
				incomingRequest.put("InvoiceAddress_city", vendorAddress.getCity());
				incomingRequest.put("InvoiceAddress_state", vendorAddress.getState());
				incomingRequest.put("InvoiceAddress_postalCode", vendorAddress.getPostalCode());
				incomingRequest.put("InvoiceAddress_country", vendorAddress.getCountry());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new TsaException(e.toString());

		}
		return null;
	}

}
