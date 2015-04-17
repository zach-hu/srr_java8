package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ReceiptLineSetValuesFromReceiptHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptLine receiptLine = (ReceiptLine) incomingRequest.get("receiptLine");
			ReceiptHeader receiptHeader = (ReceiptHeader) incomingRequest.get("receiptHeader");
			if (receiptLine == null)
			{
				receiptLine = new ReceiptLine();
			}

			if (receiptHeader != null)
			{
				receiptLine.setIcRecHeader(receiptHeader.getIcRecHeader());
				receiptLine.setCarrierCode(receiptHeader.getCarrierCode());
				receiptLine.setPackingSlip(receiptHeader.getPackingSlip());
				receiptLine.setReceiptNumber(receiptHeader.getReceiptNumber());
				receiptLine.setReceiptType(receiptHeader.getReceiptType());
			}

			result = receiptLine;
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