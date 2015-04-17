package com.tsa.puridiom.address.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class AddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("Address_addressType", "");
			incomingRequest.put("Address_addressCode", "");
			incomingRequest.put("Address_addressLine1", "");
			incomingRequest.put("Address_addressLine2", "");
			incomingRequest.put("Address_addressLine3", "");
			incomingRequest.put("Address_addressLine4", "");
			incomingRequest.put("Address_city", "");
			incomingRequest.put("Address_state", "");
			incomingRequest.put("Address_postalCode", "");
			incomingRequest.put("Address_country", "");
			incomingRequest.put("Address_dateEntered", Dates.today("", ""));
			incomingRequest.put("Address_dateExpires", Dates.today("", ""));
			incomingRequest.put("Address_status", "");
			incomingRequest.put("Address_owner", "");
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