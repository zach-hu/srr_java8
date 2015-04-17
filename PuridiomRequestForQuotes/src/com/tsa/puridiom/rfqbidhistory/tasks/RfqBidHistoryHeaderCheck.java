package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class RfqBidHistoryHeaderCheck extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			String RfqBidHistory_icRfqHeader	= (String)incomingRequest.get("RfqBidHistory_icRfqHeader");

			if (Utility.isEmpty(RfqBidHistory_icRfqHeader)) {
				this.setStatus(Status.FAILED);
				throw new TsaException("IcRfqHeader is necessary to retrieve a bid history list of different sequence");
			}
			else {
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