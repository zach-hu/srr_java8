package com.tsa.puridiom.receipt.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class ReceiptCreateFromOrderSetup extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String organizationId = (String)incomingRequest.get("organizationId");

		try
		{
			String userId = (String) incomingRequest.get("userId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");
			String icPoHeader = (String) incomingRequest.get("PoHeader_icPoHeader");
			String icReqHeader = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			String receiptMethod = (String) incomingRequest.get("receiptMethod") ;

            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = PropertiesManager.getInstance(organizationId).getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

			String today = Dates.today(userDateFormat, userTimeZone);
            UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();

			String begin = PropertiesManager.getInstance(organizationId).getProperty("Misc", "FyBegin", "1");
			String fiscalYear = Dates.getFiscalYear(new Integer(begin).intValue(), userTimeZone);

			if (Utility.isEmpty(icPoHeader))
				icPoHeader = "0";
			if (Utility.isEmpty(icReqHeader))
				icReqHeader = "0";

			incomingRequest.put("ReceiptHeader_icRecHeader", ukg.getUniqueKey().toString());
			incomingRequest.put("ReceiptHeader_icHeaderHistory", ukg.getUniqueKey().toString());
			incomingRequest.put("ReceiptHeader_icPoHeader", icPoHeader);
//			incomingRequest.put("ReceiptHeader_icReqHeader", icReqHeader);
			incomingRequest.put("ReceiptHeader_receiptDate", today);
			if (receiptMethod.equalsIgnoreCase("return")) {
				incomingRequest.put("ReceiptHeader_receiptType", "R");
			} else	if (receiptMethod.equalsIgnoreCase("ReceiveByOrder")) {
				incomingRequest.put("ReceiptHeader_receiptType", "P");
			} else {
				incomingRequest.put("ReceiptHeader_receiptType", "O");
			}
			incomingRequest.put("ReceiptHeader_receivedBy", userId);
			incomingRequest.put("ReceiptHeader_carrierCode", "");
			incomingRequest.put("ReceiptHeader_packingSlip", "");
			incomingRequest.put("ReceiptHeader_owner", userId);
			incomingRequest.put("ReceiptHeader_receiptStatus", DocumentStatus.RCV_INPROGRESS);
			incomingRequest.put("ReceiptHeader_fiscalYear", fiscalYear);
			incomingRequest.put("ReceiptHeader_receiptNumber", "");
			incomingRequest.put("ReceiptHeader_forwardTo", "");
			incomingRequest.put("ReceiptHeader_pkgsReceived", new BigDecimal(0).toString());
			incomingRequest.put("ReceiptHeader_receiptNotes", "");
			incomingRequest.put("ReceiptHeader_releaseNumber", new BigDecimal(0).toString());
			incomingRequest.put("ReceiptHeader_refDate", today);
			incomingRequest.put("ReceiptHeader_returnDate", today);
			incomingRequest.put("ReceiptHeader_timeZone", userTimeZone);

			this.setStatus(Status.SUCCEEDED);
		}
		catch(Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null;
	}
}
