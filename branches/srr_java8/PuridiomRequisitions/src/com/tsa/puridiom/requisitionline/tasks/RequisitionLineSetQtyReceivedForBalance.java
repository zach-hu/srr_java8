package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class RequisitionLineSetQtyReceivedForBalance extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine == null) {
				throw new Exception("RequisitionLine was not found");
			}
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			if (receiptLine == null) {
				return requisitionLine;
			}
			requisitionLine.setAllocated(receiptLine.getQtyReceived().add(requisitionLine.getAllocated()));


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