package com.tsa.puridiom.currcode.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.*;

public class CurrCodeSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("CurrCode_currencyCode", "");
			incomingRequest.put("CurrCode_currencyName", "");
			incomingRequest.put("CurrCode_symbol", "");
			incomingRequest.put("CurrCode_country", "");
			incomingRequest.put("CurrCode_symbolPlacement", "");
			incomingRequest.put("CurrCode_negativePlacement", "");
			incomingRequest.put("CurrCode_thousandsSeprtr", "");
			incomingRequest.put("CurrCode_decimalSeparator", "");
			incomingRequest.put("CurrCode_decimalDigits", "0");
			incomingRequest.put("CurrCode_leadingZero", "");
			incomingRequest.put("CurrCode_positiveFormat", "");
			incomingRequest.put("CurrCode_negativeFormat", "");
			incomingRequest.put("CurrCode_dateEntered", Dates.today("", ""));
			incomingRequest.put("CurrCode_dateExpires", Dates.today("", ""));
			incomingRequest.put("CurrCode_status", "");
			incomingRequest.put("CurrCode_owner", "");
			incomingRequest.put("CurrCode_conversionToBase", "0");

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