package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.Map;

public class ReceiptHeaderRetrieveVendorAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");

			if (receiptHeader == null) {
				throw new TsaException("The ReceiptHeader entity was not found.");
			}

			String	vendorCode = receiptHeader.getVendorId();
			String	contactAddr = "DEFAULT";
			incomingRequest.put("Address_addressType", vendorCode);
			incomingRequest.put("Address_addressCode", contactAddr);
			incomingRequest.put("Contact_contactCode", "001");
			incomingRequest.put("Contact_vendorId", receiptHeader.getVendorId());

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return result;
	}
}
