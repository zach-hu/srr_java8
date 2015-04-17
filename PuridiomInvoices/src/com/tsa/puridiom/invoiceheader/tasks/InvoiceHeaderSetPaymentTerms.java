package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.entity.PaymentTerm;
import com.tsagate.foundation.processengine.Task;

import java.util.Map;

public class InvoiceHeaderSetPaymentTerms extends Task {
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		PaymentTerm terms = (PaymentTerm) incomingRequest.get("paymentTerm");

		if (terms != null)
		{
			incomingRequest.put("InvoiceHeader_termsDiscperc", terms.getDiscount().toString());
			incomingRequest.put("InvoiceHeader_termsDiscdays", terms.getDiscountDays().toString());
		}

		return null;
	}

}