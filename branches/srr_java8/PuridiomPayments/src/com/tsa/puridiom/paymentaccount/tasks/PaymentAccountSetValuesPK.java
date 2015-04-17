package com.tsa.puridiom.paymentaccount.tasks;

import com.tsa.puridiom.entity.PaymentAccount;
import java.math.BigDecimal;
import java.util.Map;

public class PaymentAccountSetValuesPK
{
	public void setValues(Map incomingRequest, PaymentAccount paymentAccount ) throws Exception
	{
		try
		{
			String icPaymentString = (String) incomingRequest.get("PaymentAccount_icPayment");
			BigDecimal icPayment = new BigDecimal ( icPaymentString );
			paymentAccount.setIcPayment(icPayment);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}