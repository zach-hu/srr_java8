package com.tsa.puridiom.invoicevendor.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.sql.Date;
import java.util.Map;

public class InvoiceVendorSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvoiceVendor invoiceVendor = (InvoiceVendor) incomingRequest.get("invoiceVendor");
			if (invoiceVendor == null)
			{
				invoiceVendor = new InvoiceVendor();
			}

			if (incomingRequest.containsKey("InvoiceVendor_vendorId"))
			{
				String vendorId = (String ) incomingRequest.get("InvoiceVendor_vendorId");
				invoiceVendor.setVendorId(vendorId);
			}
			if (incomingRequest.containsKey("InvoiceVendor_vendorName"))
			{
				String vendorName = (String ) incomingRequest.get("InvoiceVendor_vendorName");
				invoiceVendor.setVendorName(vendorName);
			}
			if (incomingRequest.containsKey("InvoiceVendor_fobCode"))
			{
				String fobCode = (String ) incomingRequest.get("InvoiceVendor_fobCode");
				invoiceVendor.setFobCode(fobCode);
			}
			if (incomingRequest.containsKey("InvoiceVendor_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvoiceVendor_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invoiceVendor.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvoiceVendor_status"))
			{
				String status = (String ) incomingRequest.get("InvoiceVendor_status");
				invoiceVendor.setStatus(status);
			}
			if (incomingRequest.containsKey("InvoiceVendor_ownerEmail"))
			{
				String ownerEmail = (String ) incomingRequest.get("InvoiceVendor_ownerEmail");
				invoiceVendor.setOwnerEmail(ownerEmail);
			}
			if (incomingRequest.containsKey("InvoiceVendor_notes"))
			{
				String notes = (String ) incomingRequest.get("InvoiceVendor_notes");
				invoiceVendor.setNotes(notes);
			}
			if (incomingRequest.containsKey("InvoiceVendor_apReference"))
			{
				String apReference = (String ) incomingRequest.get("InvoiceVendor_apReference");
				invoiceVendor.setApReference(apReference);
			}
			if (incomingRequest.containsKey("InvoiceVendor_eftAccountNumber"))
			{
				String eftAccountNumber = (String) incomingRequest.get("InvoiceVendor_eftAccountNumber");
				invoiceVendor.setEftAccountNumber(eftAccountNumber);
			}
			if (incomingRequest.containsKey("InvoiceVendor_eftAccountType"))
			{
				String eftAccountType = (String) incomingRequest.get("InvoiceVendor_eftAccountType");
				invoiceVendor.setEftAccountType(eftAccountType);
			}
			if (incomingRequest.containsKey("InvoiceVendor_eftBankName"))
			{
				String eftBankName = (String) incomingRequest.get("InvoiceVendor_eftBankName");
				invoiceVendor.setEftBankName(eftBankName);
			}
			if (incomingRequest.containsKey("InvoiceVendor_eftPersonName"))
			{
				String eftPersonName = (String) incomingRequest.get("InvoiceVendor_eftPersonName");
				invoiceVendor.setEftPersonName(eftPersonName);
			}
			if (incomingRequest.containsKey("InvoiceVendor_eftRoutingNumber"))
			{
				String eftRoutingNumber = (String) incomingRequest.get("InvoiceVendor_eftRoutingNumber");
				invoiceVendor.setEftRoutingNumber(eftRoutingNumber);
			}
			if (incomingRequest.containsKey("InvoiceVendor_eftWireAccount"))
			{
				String eftWireAccount = (String) incomingRequest.get("InvoiceVendor_eftWireAccount");
				invoiceVendor.setEftWireAccount(eftWireAccount);
			}

			result = invoiceVendor;
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