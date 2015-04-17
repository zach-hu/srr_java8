package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class ReceiptLineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			incomingRequest.put("ReceiptLine_icRecLine", "0");
			incomingRequest.put("ReceiptLine_icRecHeader", "0");
			incomingRequest.put("ReceiptLine_receiptLine", "0");
			incomingRequest.put("ReceiptLine_icPoLine", "0");
			incomingRequest.put("ReceiptLine_receiptDate", Dates.today("", userTimeZone));
			incomingRequest.put("ReceiptLine_receiptNumber", "");
			incomingRequest.put("ReceiptLine_packingSlip", "");
			incomingRequest.put("ReceiptLine_lotNumber", "");
			incomingRequest.put("ReceiptLine_receivedBy", "");
			incomingRequest.put("ReceiptLine_qtyReceived", "0");
			incomingRequest.put("ReceiptLine_qtyReturned", "0");
			incomingRequest.put("ReceiptLine_inspectionCode", "");
			incomingRequest.put("ReceiptLine_status", "");
			incomingRequest.put("ReceiptLine_icPoHeader", "0");
			incomingRequest.put("ReceiptLine_qtyRejected", "0");
			incomingRequest.put("ReceiptLine_convFactor", "0");
			incomingRequest.put("ReceiptLine_udf1Code", "");
			incomingRequest.put("ReceiptLine_udf2Code", "");
			incomingRequest.put("ReceiptLine_udf3Code", "");
			incomingRequest.put("ReceiptLine_udf4Code", "");
			incomingRequest.put("ReceiptLine_udf5Code", "");
			incomingRequest.put("ReceiptLine_carrierCode", "");
			incomingRequest.put("ReceiptLine_linComflag", "");
			incomingRequest.put("ReceiptLine_receiptType", "");
			incomingRequest.put("ReceiptLine_apBatchid", "");
			incomingRequest.put("ReceiptLine_receiptNotes", "");
			incomingRequest.put("ReceiptLine_releaseNumber", "0");
			incomingRequest.put("ReceiptLine_rejectionCode", "");
			incomingRequest.put("ReceiptLine_dispositionCode", "");
			incomingRequest.put("ReceiptLine_qtyAccepted", "0");
			incomingRequest.put("ReceiptLine_duomQtyReceived", "0");
			incomingRequest.put("ReceiptLine_umCode", "");
			incomingRequest.put("ReceiptLine_duomUmCode", "");
			incomingRequest.put("ReceiptLine_qtyInspected", "0");
			incomingRequest.put("ReceiptLine_qtyMarked", "0");
			incomingRequest.put("ReceiptLine_qtyDelivered", "0");
			incomingRequest.put("ReceiptLine_inspectionStatus", DocumentStatus.INSP_PENDING) ;
			incomingRequest.put("ReceiptLine_inspLocation","") ;
			incomingRequest.put("ReceiptLine_inspArea","") ;
			incomingRequest.put("ReceiptLine_inspStorage","") ;
			incomingRequest.put("ReceiptLine_chemicalItemNumber", "");
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
