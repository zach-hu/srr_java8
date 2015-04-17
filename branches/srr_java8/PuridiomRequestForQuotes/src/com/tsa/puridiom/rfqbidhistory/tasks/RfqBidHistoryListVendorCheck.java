package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class RfqBidHistoryListVendorCheck extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			Object RfqBidHistory_vendorIdObj	= incomingRequest.get("RfqBidHistory_vendorId");
			String RfqBidHistory_vendorId[] = null;

			if (RfqBidHistory_vendorIdObj instanceof String[]) {
				RfqBidHistory_vendorId		= (String[])incomingRequest.get("RfqBidHistory_vendorId");
			}
			else {
				RfqBidHistory_vendorId = new String[1];
				RfqBidHistory_vendorId[0]	= (String) RfqBidHistory_vendorIdObj;
			}


			if (RfqBidHistory_vendorId == null || RfqBidHistory_vendorId.length == 0) {
				this.setStatus(Status.FAILED);
				throw new TsaException("VendorId list is necessary to retrieve a bid history list of different sequence");
			}
			else {
				incomingRequest.put("RfqBidHistory_vendorId", RfqBidHistory_vendorId);
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