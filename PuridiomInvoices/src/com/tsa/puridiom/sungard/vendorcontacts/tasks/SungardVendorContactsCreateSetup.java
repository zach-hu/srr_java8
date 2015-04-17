package com.tsa.puridiom.sungard.vendorcontacts.tasks;

import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.*;

public class SungardVendorContactsCreateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String organizationId = (String)incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			
			InvoiceVendor invoiceVendor = (InvoiceVendor) incomingRequest.get("invoiceVendor");
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			String	internalVendorId = (String) incomingRequest.get("internalVendorId");
			
			incomingRequest.put("SungardVendorContacts_internalVendorId", internalVendorId);
			incomingRequest.put("SungardVendorContacts_associatedAddrId", "0");
			incomingRequest.put("SungardVendorContacts_contactPhoneNum", invoiceHeader.getContactPhone());
			incomingRequest.put("SungardVendorContacts_contactFaxNum", invoiceHeader.getContactFax());
			incomingRequest.put("SungardVendorContacts_contactName", invoiceHeader.getContactName());
			incomingRequest.put("SungardVendorContacts_contactEmailAddr", invoiceHeader.getContactEmail());

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