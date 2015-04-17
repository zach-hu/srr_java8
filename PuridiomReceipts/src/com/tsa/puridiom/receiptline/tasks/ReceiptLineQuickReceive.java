package com.tsa.puridiom.receiptline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

@SuppressWarnings(value = { "unchecked" })
public class ReceiptLineQuickReceive extends Task{

	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptLine receiptLine = (ReceiptLine)incomingRequest.get("receiptLine");
			PoLine poLine = (PoLine)incomingRequest.get("poLine");

			if(poLine != null && receiptLine != null)
			{
				BigDecimal quantity = poLine.getQuantity();
				BigDecimal quantityReceived = poLine.getQtyReceived();
				
				if(receiptLine.getInspectionRequired().equalsIgnoreCase("Y"))
				{
					incomingRequest.put("ReceiptLine_qtyStep0Accepted", quantity.subtract(quantityReceived).toString());
					incomingRequest.put("ReceiptLine_qtyStep0Received", quantity.subtract(quantityReceived).toString());
					incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_STEP_1);
					incomingRequest.put("isRequiredOf", "I");
					
					receiptLine.setPoLine(poLine);
				}
				else if(!HiltonUtility.isEmpty(receiptLine.getMarkTagRequired()))
				{
					incomingRequest.put("ReceiptLine_qtyStep0Accepted", quantity.subtract(quantityReceived).toString());
					incomingRequest.put("ReceiptLine_qtyStep0Received", quantity.subtract(quantityReceived).toString());
					incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_STEP_2);
					incomingRequest.put("isRequiredOf", "M");
					
					receiptLine.setPoLine(poLine);
				} 
				else
				{
					incomingRequest.put("ReceiptLine_qtyAccepted", quantity.subtract(quantityReceived).toString());
					incomingRequest.put("ReceiptLine_qtyReceived", quantity.subtract(quantityReceived).toString());
					incomingRequest.put("ReceiptLine_status", DocumentStatus.RCV_RECEIVED);
					
					incomingRequest.put("PoLine_qtyReceived", quantity.toString());
					incomingRequest.put("PoLine_status", DocumentStatus.RCV_RECEIVED);
				}
				
				incomingRequest.put("newHistoryReceiptLine", receiptLine);
				
				this.setStatus(Status.SUCCEEDED) ;
			} else {
				
				this.setStatus(Status.FAILED) ;
				Log.error(this, "ReceiptLine has been not received because PoLine is null.");
				//throw new TsaException("ReceiptLine has been not received because PoLine is null.");
				
			}
			
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e);
			throw e;
		}
		return result;
	}

}