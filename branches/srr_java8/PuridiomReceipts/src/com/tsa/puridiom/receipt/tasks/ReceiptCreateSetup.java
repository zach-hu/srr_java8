package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class ReceiptCreateSetup extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try {
			String	userId = (String) incomingRequest.get("userId") ;
            String  userTimeZone = (String) incomingRequest.get("userTimeZone") ;
			String	icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader") ;
			String	icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader") ;
            String today = Dates.today("", userTimeZone);

			if (Utility.isEmpty(icPoHeader)) {
				icPoHeader = "0";
			}
			if (Utility.isEmpty(icReqHeader)) {
				icReqHeader = "0";
			}

			if (!incomingRequest.containsKey("ReceiptHeader_icRecHeader")) {
				// Create new ic codes
				UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
				incomingRequest.put("ReceiptHeader_icRecHeader", ukg.getUniqueKey().toString());
			}
			if (!incomingRequest.containsKey("ReceiptHeader_icPoHeader")) {
				incomingRequest.put("ReceiptHeader_icPoHeader", icPoHeader);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_icReqHeader")) {
				incomingRequest.put("ReceiptHeader_icReqHeader", icReqHeader);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_receiptDate")) {
				incomingRequest.put("ReceiptHeader_receiptDate", today);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_receiptType")) {
				incomingRequest.put("ReceiptHeader_receiptType", "");
			}
			if (!incomingRequest.containsKey("ReceiptHeader_receivedBy")) {
				incomingRequest.put("ReceiptHeader_receivedBy", userId);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_owner")) {
				incomingRequest.put("ReceiptHeader_owner", userId);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_receiptStatus")) {
				incomingRequest.put("ReceiptHeader_receiptStatus", DocumentStatus.RCV_INPROGRESS);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_refDate")) {
				incomingRequest.put("ReceiptHeader_refDate", today);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_fiscalYear")) {
				PropertiesManager propertiesManager = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")) ;
				String	fYear = Dates.getFiscalYear(Integer.parseInt(propertiesManager.getProperty("MISC","FyBegin","1")), userTimeZone) ;
				incomingRequest.put("ReceiptHeader_fiscalYear", fYear);
			}
			if (!incomingRequest.containsKey("ReceiptHeader_returnDate")) {
				incomingRequest.put("ReceiptHeader_returnDate", today);
			}
            incomingRequest.put("ReceiptHeader_timeZone", userTimeZone);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}

}