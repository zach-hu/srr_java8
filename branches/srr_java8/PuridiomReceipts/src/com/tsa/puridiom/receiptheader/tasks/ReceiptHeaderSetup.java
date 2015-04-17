package com.tsa.puridiom.receiptheader.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ReceiptHeaderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String  userTimeZone = (String) incomingRequest.get("userTimeZone") ;
            String today = Dates.today("", userTimeZone);

			incomingRequest.put("ReceiptHeader_icRecHeader", "0");
			incomingRequest.put("ReceiptHeader_icPoHeader", "0");
			incomingRequest.put("ReceiptHeader_receiptDate", today);
			incomingRequest.put("ReceiptHeader_receiptType", "");
			incomingRequest.put("ReceiptHeader_receivedBy", "");
			incomingRequest.put("ReceiptHeader_carrierCode", "");
			incomingRequest.put("ReceiptHeader_packingSlip", "");
			incomingRequest.put("ReceiptHeader_vendorId", "");
			incomingRequest.put("ReceiptHeader_owner", "");
			incomingRequest.put("ReceiptHeader_receiptStatus", "");
			incomingRequest.put("ReceiptHeader_refNumber", "");
			incomingRequest.put("ReceiptHeader_refRelease", "");
			incomingRequest.put("ReceiptHeader_refDate", today);
			incomingRequest.put("ReceiptHeader_refType", "");
			incomingRequest.put("ReceiptHeader_vendorName", "");
			incomingRequest.put("ReceiptHeader_receiptNumber", "");
			incomingRequest.put("ReceiptHeader_fiscalYear", "");
			incomingRequest.put("ReceiptHeader_forwardTo", "");
			incomingRequest.put("ReceiptHeader_pkgsReceived", "0.0");
			incomingRequest.put("ReceiptHeader_receiptNotes", "");
			incomingRequest.put("ReceiptHeader_releaseNumber", "0");
			incomingRequest.put("ReceiptHeader_returnDate", today);
			incomingRequest.put("ReceiptHeader_tempIc", "0");
			incomingRequest.put("ReceiptHeader_inspectionRequired", "N");
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
