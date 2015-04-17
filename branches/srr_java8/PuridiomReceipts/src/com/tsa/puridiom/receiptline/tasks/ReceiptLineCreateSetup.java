package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.* ;
import com.tsagate.foundation.processengine.Status;
import java.util.*;

/**
 * @author Kelli
 */
public class ReceiptLineCreateSetup extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map) object;

		try {
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			String icRecHeader = (String) incomingRequest.get("ReceiptLine_icRecHeader");
			String icPoLine = (String) incomingRequest.get("ReceiptLine_icPoLine");
			String icReqLine = (String) incomingRequest.get("ReceiptLine_icReqLine");
			String	receiptNumber = (String) incomingRequest.get("ReceiptHeader_receiptNumber");
			String	releaseNumber = (String) incomingRequest.get("ReceiptHeader_releaseNumber");
			String	receiptType = (String) incomingRequest.get("ReceiptHeader_receiptType");
			String userId = (String) incomingRequest.get("userId");
			String userTimeZone = (String) incomingRequest.get("userTimeZone");
			String receiptMethod = HiltonUtility.ckNull((String) incomingRequest.get("receiptMethod"));

			incomingRequest.put("ReceiptLine_icRecLine", ukg.getUniqueKey().toString());
			if (!incomingRequest.containsKey("ReceiptLine_receivedBy")) {
				incomingRequest.put("ReceiptLine_receivedBy", userId);
			}
			if (!incomingRequest.containsKey("ReceiptLine_receiptDate")) {
				incomingRequest.put("ReceiptLine_receiptDate", Dates.today("yyyy-MM-dd", userTimeZone));
			}
			if (!incomingRequest.containsKey("ReceiptLine_receiptNumber")) {
				incomingRequest.put("ReceiptLine_receiptNumber", receiptNumber);
			}
			if (!incomingRequest.containsKey("ReceiptLine_releaseNumber")) {
				incomingRequest.put("ReceiptLine_releaseNumber", releaseNumber);
			}
			if (!incomingRequest.containsKey("ReceiptLine_receiptType")) {
				incomingRequest.put("ReceiptLine_receiptType", receiptType);
			}
			if (!incomingRequest.containsKey("ReceiptLine_timeZone")) {
				incomingRequest.put("ReceiptLine_timeZone", userTimeZone);
			}
			if (receiptMethod.equals("Adjustment"))
			{
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep0Received")) {
					incomingRequest.put("ReceiptLine_qtyStep0Received", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep0Rejected")) {
					incomingRequest.put("ReceiptLine_qtyStep0Rejected", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep0Accepted")) {
					incomingRequest.put("ReceiptLine_qtyStep0Accepted", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep1Received")) {
					incomingRequest.put("ReceiptLine_qtyStep1Received", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep1Rejected")) {
					incomingRequest.put("ReceiptLine_qtyStep1Rejected", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep1Accepted")) {
					incomingRequest.put("ReceiptLine_qtyStep1Accepted", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep2Received")) {
					incomingRequest.put("ReceiptLine_qtyStep2Received", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep2Rejected")) {
					incomingRequest.put("ReceiptLine_qtyStep2Rejected", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep2Accepted")) {
					incomingRequest.put("ReceiptLine_qtyStep2Accepted", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep3Received")) {
					incomingRequest.put("ReceiptLine_qtyStep3Received", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep3Rejected")) {
					incomingRequest.put("ReceiptLine_qtyStep3Rejected", "0");
				}
				if (!incomingRequest.containsKey("ReceiptLine_qtyStep3Accepted")) {
					incomingRequest.put("ReceiptLine_qtyStep3Accepted", "0");
				}
				if (incomingRequest.containsKey("ReceiptLine_qtyReceived")) {
					incomingRequest.put("ReceiptLine_qtyStep0Received", incomingRequest.get("ReceiptLine_qtyReceived"));
				}
				if (incomingRequest.containsKey("ReceiptLine_qtyRejected")) {
					incomingRequest.put("ReceiptLine_qtyStep0Rejected", incomingRequest.get("ReceiptLine_qtyRejected"));
				}
				if (incomingRequest.containsKey("ReceiptLine_qtyAccepted")) {
					incomingRequest.put("ReceiptLine_qtyStep0Accepted", incomingRequest.get("ReceiptLine_qtyAccepted"));
				}
			}
			incomingRequest.put("ReceiptHeader_icRecHeader", icRecHeader) ;
			incomingRequest.put("PoLine_icPoLine", icPoLine) ;
			incomingRequest.put("RequisitionLine_icReqLine", icReqLine) ;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			throw e;
		}

		return null ;
	}
}
