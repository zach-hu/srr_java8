package com.tsa.puridiom.currcode.tasks;

import java.util.Map;
import com.tsa.puridiom.entity.CurrCode;

public class CurrCodeSetValuesPK
{
	public void setValues(Map incomingRequest, CurrCode currcode ) throws Exception
	{
		try
		{
			String currencyCode = (String ) incomingRequest.get("CurrCode_currencyCode");
			currcode.setCurrencyCode(currencyCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}