package com.tsa.puridiom.purchasecard.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class PurchaseCardSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("PurchaseCard_pcardCode", "");
			incomingRequest.put("PurchaseCard_pcardNumber", "");
			incomingRequest.put("PurchaseCard_pcardSecureCode", "");
			incomingRequest.put("PurchaseCard_pcardHolderName", "");
			incomingRequest.put("PurchaseCard_pcardExpires", "");
			incomingRequest.put("PurchaseCard_pcardBank", "");
			incomingRequest.put("PurchaseCard_dateEntered", Dates.today("", ""));
			incomingRequest.put("PurchaseCard_dateExpires", Dates.today("", ""));
			incomingRequest.put("PurchaseCard_status", "");
			incomingRequest.put("PurchaseCard_owner", "");

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