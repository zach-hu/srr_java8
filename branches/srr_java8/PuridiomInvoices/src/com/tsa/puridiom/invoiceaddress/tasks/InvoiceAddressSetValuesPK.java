package com.tsa.puridiom.invoiceaddress.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceAddressPK;

public class InvoiceAddressSetValuesPK
{
	public void setValues(Map incomingRequest, InvoiceAddress invoiceAddress ) throws Exception
	{
		try
		{
			String vendorId = (String ) incomingRequest.get("InvoiceAddress_vendorId");
			String addressCode = (String ) incomingRequest.get("InvoiceAddress_addressCode");
			InvoiceAddressPK comp_id = new InvoiceAddressPK();
			comp_id.setAddressCode(addressCode);
			comp_id.setVendorId(vendorId);
			invoiceAddress.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}