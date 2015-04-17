package com.tsa.puridiom.payment.tasks;
import java.util.Map;

import com.tsagate.foundation.processengine.Task;

public class PaymentSetup extends Task
{
	public void executeTask(Map incomingRequest) throws Exception
	{
		incomingRequest.put("Payment.icPoHeader", "0");
		incomingRequest.put("Payment.sequence", "0");
		incomingRequest.put("Payment.icPoLine", "0");
		incomingRequest.put("Payment.poNumber", "");
		incomingRequest.put("Payment.revisionNumber", "0");
		incomingRequest.put("Payment.releaseNumber", "0");
		incomingRequest.put("Payment.paymentDate", "2003-10-31");
		incomingRequest.put("Payment.invoiceNumber", "");
		incomingRequest.put("Payment.paymentAmount", "0");
		incomingRequest.put("Payment.checkNumber", "");
		incomingRequest.put("Payment.voucherNumber", "");
	}
}