package com.tsa.puridiom.paymentterm.tasks;

import com.tsa.puridiom.entity.PaymentTerm;
import java.util.Map;

public class PaymentTermSetValuesPK
{
	public void setValues(Map incomingRequest, PaymentTerm paymentterm ) throws Exception
	{
		try
		{
			String termsCode = (String ) incomingRequest.get("PaymentTerm_termsCode");
			paymentterm.setTermsCode(termsCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}