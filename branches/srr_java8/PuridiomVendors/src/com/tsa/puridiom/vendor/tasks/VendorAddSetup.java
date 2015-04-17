/*
 * Created on Dec 22, 2003
 * @author renzo
 * com.tsa.puridiom.tasks.vendorVendorAddSetup.java
 */

package com.tsa.puridiom.vendor.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.AddressPK;
import com.tsa.puridiom.entity.Contact;
import com.tsa.puridiom.entity.ContactPK;
import com.tsa.puridiom.entity.Vendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;


public class VendorAddSetup extends Task
{
	/**
	 * Method executeTask.
	 *
	 * @param object <p>incomingRequest</p>
	 */
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;

			String	originalVendorName = (String) incomingRequest.get("originalVendor_vendorName");

			if (!Utility.isEmpty(originalVendorName)) {
			    incomingRequest.put("Vendor_vendorName", originalVendorName);
			    incomingRequest.remove("originalVendor_vendorName");
			}

			Vendor vendor = new Vendor();
			Address address = new Address();
			Contact contact = new Contact();
			ContactPK contactPk = new ContactPK();
			ret = vendor;
			String supplierId = (String)incomingRequest.get("Vendor_vendorId");
			contact.setAddressCode("DEFAULT");
			contactPk.setContactCode("001");
			contactPk.setContactType("DEFAULT");
			contactPk.setVendorId(supplierId);
			contact.setComp_id(contactPk);
			contact.setAddressCode("DEFAULT");
			contact.setStatus("02");
			incomingRequest.put("contact", contact);
			incomingRequest.put("Contact_vendorId", supplierId);

			AddressPK addPK = new AddressPK();
			addPK.setAddressCode("DEFAULT");
			addPK.setAddressType(supplierId);
            address.setComp_id(addPK);
            address.setAddressLine1(originalVendorName);
            address.setAddrFld16("Y");  // Default as a Purchasing Address

			incomingRequest.put("address",	address);
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}
}
