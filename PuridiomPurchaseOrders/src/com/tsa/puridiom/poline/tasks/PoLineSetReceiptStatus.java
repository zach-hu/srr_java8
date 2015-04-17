package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoLineSetReceiptStatus extends Task
{
	@SuppressWarnings("unchecked")
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoLine poLine = (PoLine) incomingRequest.get("poLine");
			if (poLine == null)
			{
				throw new Exception("PoLine was not found");
			}
			BigDecimal qtyReceived = poLine.getQtyReceived();
			BigDecimal qtyOrdered = poLine.getQuantity();
			
			if (qtyReceived.compareTo(qtyOrdered) >= 0) 
			{
				poLine.setStatus(DocumentStatus.RCV_RECEIVED);
				incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_RECEIVED);
			} 
			else if (qtyReceived.compareTo(new BigDecimal(0)) == 1) 
			{
				poLine.setStatus(DocumentStatus.RCV_PARTIALLYRECEIVED);
				incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_PARTIALLYRECEIVED);
			} 
			else 
			{
				poLine.setStatus(DocumentStatus.PO_AWARDED);
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