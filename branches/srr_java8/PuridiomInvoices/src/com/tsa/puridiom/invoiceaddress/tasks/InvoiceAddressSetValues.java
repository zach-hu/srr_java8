package com.tsa.puridiom.invoiceaddress.tasks;

import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceAddressPK;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;

public class InvoiceAddressSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceAddressPK comp_id = new InvoiceAddressPK();
			InvoiceAddress invoiceAddress = (InvoiceAddress) incomingRequest.get("invoiceAddress");
			if (invoiceAddress == null)
			{
				invoiceAddress = new InvoiceAddress();
			}

			if (incomingRequest.containsKey("InvoiceAddress_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("InvoiceAddress_vendorId");
				comp_id.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvoiceAddress_addressCode"))
			{
				String addressCode = (String ) incomingRequest.get("InvoiceAddress_addressCode");
				comp_id.setAddressCode(addressCode);
			}
			if (incomingRequest.containsKey("InvoiceAddress_addressLine1"))
			{
				String addressLine1 = (String ) incomingRequest.get("InvoiceAddress_addressLine1");
				invoiceAddress.setAddressLine1(addressLine1);
			}
			if (incomingRequest.containsKey("InvoiceAddress_addressLine2"))
			{
				String addressLine2 = (String ) incomingRequest.get("InvoiceAddress_addressLine2");
				invoiceAddress.setAddressLine2(addressLine2);
			}
			if (incomingRequest.containsKey("InvoiceAddress_addressLine3"))
			{
				String addressLine3 = (String ) incomingRequest.get("InvoiceAddress_addressLine3");
				invoiceAddress.setAddressLine3(addressLine3);
			}
			if (incomingRequest.containsKey("InvoiceAddress_addressLine4"))
			{
				String addressLine4 = (String ) incomingRequest.get("InvoiceAddress_addressLine4");
				invoiceAddress.setAddressLine4(addressLine4);
			}
			if (incomingRequest.containsKey("InvoiceAddress_city"))
			{
				String city = (String ) incomingRequest.get("InvoiceAddress_city");
				invoiceAddress.setCity(city);
			}
			if (incomingRequest.containsKey("InvoiceAddress_state"))
			{
				String state = (String ) incomingRequest.get("InvoiceAddress_state");
				invoiceAddress.setState(state);
			}
			if (incomingRequest.containsKey("InvoiceAddress_postalCode"))
			{
				String postalCode = (String ) incomingRequest.get("InvoiceAddress_postalCode");
				invoiceAddress.setPostalCode(postalCode);
			}
			if (incomingRequest.containsKey("InvoiceAddress_country"))
			{
				String country = (String ) incomingRequest.get("InvoiceAddress_country");
				invoiceAddress.setCountry(country);
			}
			if (incomingRequest.containsKey("InvoiceAddress_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvoiceAddress_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invoiceAddress.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvoiceAddress_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvoiceAddress_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invoiceAddress.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvoiceAddress_status"))
			{
				String status = (String ) incomingRequest.get("InvoiceAddress_status");
				invoiceAddress.setStatus(status);
			}
			if (incomingRequest.containsKey("InvoiceAddress_owner"))
			{
				String owner = (String ) incomingRequest.get("InvoiceAddress_owner");
				invoiceAddress.setOwner(owner);
			}
			invoiceAddress.setComp_id(comp_id);

			result = invoiceAddress;
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