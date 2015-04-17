package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class RfqBidHistoryListQuantityCheck extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			Object RfqBidHistory_quantityObj	= incomingRequest.get("RfqBidHistory_quantity");
			String RfqBidHistory_quantity[]		= null;

			if (RfqBidHistory_quantityObj instanceof String[]) {
				RfqBidHistory_quantity		= (String[]) RfqBidHistory_quantityObj;
			}
			else {
				RfqBidHistory_quantity = new String[1];
				RfqBidHistory_quantity[0]	= (String) RfqBidHistory_quantityObj;
			}

			if (RfqBidHistory_quantity == null || RfqBidHistory_quantity.length == 0) {
				this.setStatus(Status.FAILED);
				throw new TsaException("Quantity list is necessary to set a bid history list");
			}
			else {
				incomingRequest.put("RfqBidHistory_quantity", RfqBidHistory_quantity);
				this.setStatus(Status.SUCCEEDED);
			}
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}