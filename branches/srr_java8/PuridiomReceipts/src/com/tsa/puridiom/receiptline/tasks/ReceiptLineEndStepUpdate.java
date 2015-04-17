package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * 
 * @author Alexander
 *
 */
public class ReceiptLineEndStepUpdate extends Task {

	@SuppressWarnings("unchecked")
	public Object executeTask (Object object) throws Exception {

		Map incomingRequest = (Map)object;

		ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
		if (receiptLine != null) {
			if (receiptLine.getStatus().equals(DocumentStatus.RCV_STEP_3) && receiptLine.getInspectionRequired().equals("Y")) 
			{

				BigDecimal qtyStep0Received = HiltonUtility.ckNull(receiptLine.getQtyStep0Received());
				BigDecimal qtyStep3Accepted = HiltonUtility.ckNull(receiptLine.getQtyStep3Accepted());

				BigDecimal qtyStep0Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep0Rejected());
				BigDecimal qtyStep1Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep1Rejected());
				BigDecimal qtyStep2Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep2Rejected());
				BigDecimal qtyStep3Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep3Rejected());

				BigDecimal qtyTotalRejected = qtyStep0Rejected.add(qtyStep1Rejected).add(qtyStep2Rejected).add(qtyStep3Rejected);

				receiptLine.setQtyReceived(qtyStep0Received);
				receiptLine.setQtyAccepted(qtyStep3Accepted);
				receiptLine.setQtyRejected(qtyTotalRejected);

				// Update Receipt Line
				ReceiptLineUpdateById receiptLineTask = new ReceiptLineUpdateById() ;

				Map updateParameters = new HashMap();
				updateParameters.put("userId", incomingRequest.get("userId"));
				updateParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
				updateParameters.put("organizationId", incomingRequest.get("organizationId"));
				updateParameters.put("dbsession", incomingRequest.get("dbsession"));
				updateParameters.put("receiptLine", receiptLine);

				receiptLineTask.executeTask(updateParameters);
			}

			this.setStatus(Status.SUCCEEDED);

		} else {
			Log.error(this, "Empty list of lines");
		}

		return null;
	}
}
