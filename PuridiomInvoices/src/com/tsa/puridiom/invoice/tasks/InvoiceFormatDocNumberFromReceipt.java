package com.tsa.puridiom.invoice.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.ReceiptHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class InvoiceFormatDocNumberFromReceipt extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ReceiptHeader receiptHeader = (ReceiptHeader)incomingRequest.get("receiptHeader");
			String receiptNumber = receiptHeader.getReceiptNumber();
			String invoiceNumber = "";
			if (receiptHeader.getReleaseNumber().compareTo(new BigDecimal(0)) == 0)
			{
				 invoiceNumber = "PS-" + receiptNumber;
			}
			else
			{
				 invoiceNumber = "PS-" + receiptNumber + "-A" + receiptHeader.getReleaseNumber().toString();
			}

			result = invoiceNumber;

			this.setStatus(Status.SUCCEEDED);
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
