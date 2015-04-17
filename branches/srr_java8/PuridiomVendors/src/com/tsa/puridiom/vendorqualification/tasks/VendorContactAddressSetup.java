package com.tsa.puridiom.vendorqualification.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.VendorRegister;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class VendorContactAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    VendorRegister vendorRegister = (VendorRegister) incomingRequest.get("vendorRegister");
		    String	vendorId = (String) incomingRequest.get("Vendor_vendorId");
		    String	contactCode = (String) incomingRequest.get("Contact_contactCode");
		    String	addressCode = "";

		    if (vendorRegister == null) {
		        throw new Exception("Contact Address cannot be setup because vendorRegister was not set.");
		    }
		    if (HiltonUtility.isEmpty(vendorId)) {
		        throw new Exception("Contact Address cannot be setup because Vendor_vendorId was empty.");
		    }
		    if (HiltonUtility.isEmpty(contactCode) || contactCode.equals("001")) {
		        addressCode = "DEFAULT";
		    } else {
		        addressCode = contactCode;
		    }

			incomingRequest.put("Address_addressType", vendorId);
			incomingRequest.put("Address_addressCode", addressCode);
			incomingRequest.put("Address_addressLine1", vendorRegister.getVendorName());
			incomingRequest.put("Address_addressLine2", vendorRegister.getAddressLine2());
			incomingRequest.put("Address_addressLine3", vendorRegister.getAddressLine3());
			incomingRequest.put("Address_addressLine4", vendorRegister.getAddressLine4());
			incomingRequest.put("Address_city", vendorRegister.getAddressCity());
			incomingRequest.put("Address_state", vendorRegister.getAddressState());
			incomingRequest.put("Address_postalCode", vendorRegister.getAddressZipCode());
			incomingRequest.put("Address_country", vendorRegister.getAddressCountry());
			incomingRequest.put("Address_dateEntered", Dates.today("", ""));
			incomingRequest.put("Address_dateExpires", Dates.today("", ""));
			incomingRequest.put("Address_status", "02");
			incomingRequest.put("Address_owner", "PURIDIOM-BB");
			incomingRequest.put("Address_inventory", "");
			incomingRequest.put("Address_shipTo", "");
			incomingRequest.put("Address_billTo", "");
			incomingRequest.put("Address_invoiceTo", "");
			incomingRequest.put("Address_issuedBy", "");
			incomingRequest.put("Address_offerTo", "");
			incomingRequest.put("Address_administeredBy", "");
			incomingRequest.put("Address_paymentBy", "");
			incomingRequest.put("Address_puchaseFor", "");
			incomingRequest.put("Address_addrFld10", "");
			incomingRequest.put("Address_addrFld11", "");
			incomingRequest.put("Address_addrFld12", "");
			incomingRequest.put("Address_addrFld13", "");
			incomingRequest.put("Address_addrFld14", "");
			incomingRequest.put("Address_addrFld15", "");
			incomingRequest.put("Address_addrFld16", "");
			incomingRequest.put("Address_addrFld17", "");
			incomingRequest.put("Address_addrFld18", "");
			incomingRequest.put("Address_addrFld19", "");
			incomingRequest.put("Address_addrFld20", "");

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