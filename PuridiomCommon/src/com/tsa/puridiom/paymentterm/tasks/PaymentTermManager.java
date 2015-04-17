package com.tsa.puridiom.paymentterm.tasks;

import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.CatalogItem;
import com.tsa.puridiom.entity.PaymentTerm;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Utility;

public class PaymentTermManager
{
	private static PaymentTermManager INSTANCE = new PaymentTermManager();

	private PaymentTermManager()
	{
		super();
	}

	public static PaymentTermManager getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new PaymentTermManager();
		}
		return INSTANCE;
	}

	public Object getPaymentTerm(String organizationId, String termsCode) throws java.lang.Exception
	{
		Object result = null;
		try
		{
			if (Utility.isEmpty(termsCode) || Utility.isEmpty(organizationId))
			{
				result = new PaymentTerm();
				return result;
			}
			else
			{
				Map incomingRequest = new HashMap();
				incomingRequest.put("organizationId", organizationId);
				incomingRequest.put("PaymentTerm_termsCode", termsCode);

				PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
				PuridiomProcess process = processLoader.loadProcess("paymentterm-retrieve-by-id.xml");

				process.executeProcess(incomingRequest);

				result = incomingRequest.get("paymentTerm");
				if (result == null)
				{
					result = new PaymentTerm();
				}
			}
			return result;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}