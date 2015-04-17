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
public class ReceiptLinesEndStepUpdate extends Task {

	@SuppressWarnings("unchecked")
	public Object executeTask (Object object) throws Exception {

		Map incomingRequest = (Map)object;

		List receiptLineList = (List) incomingRequest.get("receiptLineList");
		if (receiptLineList != null && receiptLineList.size() > 0) {
			Iterator iterator = receiptLineList.iterator();
			while (iterator.hasNext())
			{
				try
				{
					ReceiptLine receiptLine = (ReceiptLine) iterator.next();

					if (receiptLine.getStatus().equals(DocumentStatus.RCV_STEP_3) || receiptLine.getStatus().equals(DocumentStatus.RCV_REJECTED))
					{

						BigDecimal qtyReceived = HiltonUtility.ckNull(receiptLine.getQtyStep0Received());
						//BigDecimal qtyAccepted = this.getQtyAccepted(receiptLine);
						BigDecimal qtyAccepted = HiltonUtility.ckNull(receiptLine.getQtyStep3Accepted());
						BigDecimal qty3Received = HiltonUtility.ckNull(receiptLine.getQtyStep3Received());
						BigDecimal qtyRejected = qtyReceived.subtract(qtyAccepted);
						if (qtyAccepted.compareTo(new BigDecimal(0)) == 0 && qty3Received.compareTo(new BigDecimal(0))!=0)
						{
							receiptLine.setStatus(DocumentStatus.RCV_REJECTED);
						}
						//BigDecimal qtyStep3Accepted = HiltonUtility.ckNull(receiptLine.getQtyStep3Accepted());

						//BigDecimal qtyStep0Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep0Rejected());
						//BigDecimal qtyStep1Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep1Rejected());
						//BigDecimal qtyStep2Rejected = HiltonUtility.ckNull(receiptLine.getQtyStep2Rejected());


						//BigDecimal qtyTotalRejected = qtyStep0Rejected.add(qtyStep1Rejected).add(qtyStep2Rejected);

						receiptLine.setQtyReceived(qtyReceived);
						//receiptLine.setQtyAccepted(qtyAccepted);
						receiptLine.setQtyRejected(qtyRejected);
						receiptLine.setQtyAccepted(qtyAccepted);
						
						receiptLine.setQtyInspected(receiptLine.getQtyStep1Accepted());
						receiptLine.setQtyMarked(receiptLine.getQtyStep2Accepted());
						receiptLine.setQtyDelivered(receiptLine.getQtyStep3Accepted());
						
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
				}
				catch (Exception e)
				{
					this.setStatus(Status.FAILED);
					Log.error(this, e);
					throw e;
				}
			}

			this.setStatus(Status.SUCCEEDED);

		} else {
			Log.error(this, "Empty list of lines");
		}

		return null;
	}

	public BigDecimal getQtyAccepted(ReceiptLine receiptLine)
	{
		BigDecimal qtyAccepted = null;
		if(HiltonUtility.ckNull(receiptLine.getQtyStep3Accepted()).compareTo(new BigDecimal("0")) != 0)
			qtyAccepted = receiptLine.getQtyStep3Accepted();
		else  if (HiltonUtility.ckNull(receiptLine.getQtyStep2Accepted()).compareTo(new BigDecimal("0")) != 0)
			qtyAccepted = receiptLine.getQtyStep2Accepted();
		else if (receiptLine.getInspectionRequired().equalsIgnoreCase("Y"))
			qtyAccepted = HiltonUtility.ckNull(receiptLine.getQtyStep1Accepted());
		else
			qtyAccepted = HiltonUtility.ckNull(receiptLine.getQtyStep0Accepted());

		return qtyAccepted;
	}
}
