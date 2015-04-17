package com.tsa.puridiom.requisitionline.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class RequisitionLineSetReceiptStatus extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine == null)
			{
				throw new Exception("RequistionLine was not found");
			}
			BigDecimal qtyReceived = requisitionLine.getAllocated();
			BigDecimal qtyOrdered = requisitionLine.getQuantity();

			if (qtyReceived.compareTo(qtyOrdered) >= 0) {
				requisitionLine.setStatus(DocumentStatus.RCV_RECEIVED);
				incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_RECEIVED);
			} else if (qtyReceived.compareTo(new BigDecimal(0)) == 1) {
				requisitionLine.setStatus(DocumentStatus.RCV_PARTIALLYRECEIVED);
				incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_PARTIALLYRECEIVED);
			} /*else if(requisitionLine.getStatus().compareTo(DocumentStatus.INV_BACKORDERED) == 0){
				requisitionLine.setStatus(DocumentStatus.INV_BACKORDERED);
			} else {
				requisitionLine.setStatus(DocumentStatus.REQ_APPROVED);
			}*/

			result = requisitionLine;
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