package com.tsa.puridiom.invoiceaddress.tasks;

import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;

import java.util.Map;

public class InvoiceAddressCreate extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			InvoiceAddress invoiceAddress = new InvoiceAddress();
			String prefix = "00";
			String addressCode = (String) incomingRequest.get("InvoiceAddress_addressCode");
			if (addressCode.length() > 1)
			{
				prefix = "0";
			}
			String newAddressCode = prefix.concat(addressCode);
			incomingRequest.put("InvoiceAddress_addressCode", newAddressCode);
			incomingRequest.put("InvoiceHeader_vendorAddrCode", newAddressCode);

			incomingRequest.put("InvoiceAddress_dateEntered", Dates.today("", userTimeZone));
			incomingRequest.put("InvoiceAddress_dateExpires", Dates.today("", userTimeZone));
			incomingRequest.put("InvoiceAddress_status", "02");
			incomingRequest.put("InvoiceAddress_owner", (String) incomingRequest.get("userId"));

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