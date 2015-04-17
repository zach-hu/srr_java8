package com.tsa.puridiom.invoiceheader.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvoiceHeaderSetInvoiceNumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String isValidNumber = HiltonUtility.ckNull((String) incomingRequest.get("isValidNumber"));
			if (isValidNumber.equalsIgnoreCase("FALSE"))
			{
				incomingRequest.put("InvoiceHeader_invoiceNumber", (String) incomingRequest.get("originalInvoiceNumber"));
			}

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