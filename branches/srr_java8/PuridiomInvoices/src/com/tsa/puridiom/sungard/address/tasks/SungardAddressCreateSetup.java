package com.tsa.puridiom.sungard.address.tasks;

import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsa.puridiom.entity.InvoiceVendor;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import java.util.Map;

public class SungardAddressCreateSetup extends Task
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
			InvoiceAddress invoiceAddress = (InvoiceAddress) incomingRequest.get("invoiceAddress");
			String	internalVendorId = (String) incomingRequest.get("internalVendorId");
			
			incomingRequest.put("SungardAddress_internalVendorId", internalVendorId);
			incomingRequest.put("SungardAddress_associatedAddrId", "0");
			incomingRequest.put("SungardAddress_addressTypeInd", "R");
			incomingRequest.put("SungardAddress_defaultInd", "");
			incomingRequest.put("SungardAddress_state", invoiceAddress.getState());
			incomingRequest.put("SungardAddress_zipcode", invoiceAddress.getPostalCode());
			incomingRequest.put("SungardAddress_countryCode", invoiceAddress.getCountry());
			incomingRequest.put("SungardAddress_genericName2", "");
			incomingRequest.put("SungardAddress_genericName3", "");
			incomingRequest.put("SungardAddress_address1", invoiceAddress.getAddressLine1());;
			incomingRequest.put("SungardAddress_address2", invoiceAddress.getAddressLine2());
			incomingRequest.put("SungardAddress_address3",invoiceAddress.getAddressLine3());
			incomingRequest.put("SungardAddress_city", invoiceAddress.getCity());

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