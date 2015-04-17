package com.tsa.puridiom.receiptheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptHeaderRetrieveShipToAddressSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			if (receiptHeader == null) {
				throw new Exception("The ReceiptHeader entity was not found.");
			}
			String shipToCode = receiptHeader.getShipToCode();
			String addressType = "ADDR";

			/**
			 *  added on 03.14.07 for VSE  - users may enter a 1-time shipto address on the requisition
			 *  if they do, the address type is SHIPTO
			 *  and the address code is the requisition number
			 **/
			/*if (shipToCode.equals(receiptHeader.getRequisitionNumber()))
			{
				addressType = "SHIPTO";
			}*/

			incomingRequest.put("Address_addressCode", shipToCode);
			incomingRequest.put("Address_addressType", addressType);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return result ;
	}
}
