package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class PoLineSetQtyReceivedForBalance extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String receiptType = "P" ;
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			if (poLine == null) {
				throw new Exception("PoLine was not found");
			}
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			if (receiptHeader != null) {
				receiptType = receiptHeader.getReceiptType();
			}
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			if (receiptLine == null) {
				return poLine;
			}
			if (! receiptType.equals("R")) {
				poLine.setQtyReceived(receiptLine.getQtyAccepted().add(poLine.getQtyReceived()));
				poLine.setDuomQtyReceived(receiptLine.getDuomQtyReceived().add(poLine.getDuomQtyReceived()));
			} else {
				poLine.setQtyReceived(poLine.getQtyReceived().subtract(receiptLine.getQtyReturned()));
				poLine.setDuomQtyReceived(poLine.getDuomQtyReceived().subtract(receiptLine.getDuomQtyReceived()));
			}


			result = poLine;
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