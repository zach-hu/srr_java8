package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ReceiptRetrieveSetup extends Task {

	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;

		try {
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");

			if (receiptHeader == null) {
				throw new Exception ("ReceiptHeader was not found.");
			}

			String icHeader = String.valueOf(receiptHeader.getIcRecHeader());
			String icLine = "0";

			incomingRequest.put("ReceiptLine_icRecHeader", icHeader);
			incomingRequest.put("Account_icHeader",icHeader);
			incomingRequest.put("Account_icLine",icLine);
			incomingRequest.put("DocComment_icHeader",icHeader);
			incomingRequest.put("DocAttachment_icHeader", icHeader);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}