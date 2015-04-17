package com.tsa.puridiom.validationrules.receipt.tasks;

import com.tsa.puridiom.entity.ReceiptLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ReceiptAtLeastOneQuantiyReceivedRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String atLeastOneQuantiyReceived = "N";

		try
		{
			List lineItems = (List) incomingRequest.get("lineitems");

			if (lineItems != null && lineItems.size() > 0)
			{
				for (int i = 0; i < lineItems.size(); i++)
				{
					ReceiptLine recLine = (ReceiptLine) lineItems.get(i);
					if (recLine != null && recLine.getQtyReceived().compareTo(new BigDecimal(0)) > 0)
					{
						atLeastOneQuantiyReceived = "Y";
					}
				}
			}

			incomingRequest.put("atLeastOneQuantiyReceived", atLeastOneQuantiyReceived);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("An Error occurred at AccountExistRules", e);
		}
		return null;
	}
}
