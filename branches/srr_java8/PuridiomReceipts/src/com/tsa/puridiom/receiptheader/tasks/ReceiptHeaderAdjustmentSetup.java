package com.tsa.puridiom.receiptheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.UniqueKeyGenerator;

import java.util.Map;

public class ReceiptHeaderAdjustmentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String	uid = (String) incomingRequest.get("userId");
            String  userTimeZone = (String) incomingRequest.get("userTimeZone") ;
            String today = Dates.today("", userTimeZone);
			ReceiptHeader originalReceiptHeader = (ReceiptHeader) incomingRequest.get("originalReceiptHeader");

			// Create new ic code
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			incomingRequest.put("ReceiptHeader_icRecHeader", ukg.getUniqueKey().toString());

			incomingRequest.put("ReceiptHeader_icPoHeader", String.valueOf(originalReceiptHeader.getIcPoHeader()));
			incomingRequest.put("ReceiptHeader_receiptDate", today);
			incomingRequest.put("ReceiptHeader_receiptType", "A");
			incomingRequest.put("ReceiptHeader_receivedBy", uid);
			incomingRequest.put("ReceiptHeader_carrierCode", originalReceiptHeader.getCarrierCode());
			incomingRequest.put("ReceiptHeader_packingSlip", originalReceiptHeader.getPackingSlip());
			incomingRequest.put("ReceiptHeader_vendorId", originalReceiptHeader.getVendorId());
			incomingRequest.put("ReceiptHeader_owner", uid);
			incomingRequest.put("ReceiptHeader_receiptStatus", DocumentStatus.RCV_INPROGRESS);
			incomingRequest.put("ReceiptHeader_refNumber", originalReceiptHeader.getRefNumber());
			incomingRequest.put("ReceiptHeader_refRelease", originalReceiptHeader.getRefRelease());
			incomingRequest.put("ReceiptHeader_refDate", today);
			incomingRequest.put("ReceiptHeader_refType", originalReceiptHeader.getRefType());
			incomingRequest.put("ReceiptHeader_vendorName", originalReceiptHeader.getVendorName());
			incomingRequest.put("ReceiptHeader_receiptNumber", originalReceiptHeader.getReceiptNumber());
			incomingRequest.put("ReceiptHeader_fiscalYear", originalReceiptHeader.getFiscalYear());
			incomingRequest.put("ReceiptHeader_forwardTo", originalReceiptHeader.getForwardTo());
			incomingRequest.put("ReceiptHeader_pkgsReceived", String.valueOf(originalReceiptHeader.getPkgsReceived()));
			incomingRequest.put("ReceiptHeader_icReqHeader", String.valueOf(originalReceiptHeader.getIcReqHeader()));
			incomingRequest.put("ReceiptHeader_shipToCode", originalReceiptHeader.getShipToCode());
			incomingRequest.put("ReceiptHeader_icHeaderHistory", String.valueOf(originalReceiptHeader.getIcHeaderHistory()));
			incomingRequest.put("ReceiptHeader_itemLocation", originalReceiptHeader.getItemLocation());


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