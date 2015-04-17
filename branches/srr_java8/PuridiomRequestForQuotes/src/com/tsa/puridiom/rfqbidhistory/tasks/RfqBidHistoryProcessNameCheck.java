package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.Map;

public class RfqBidHistoryProcessNameCheck extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			String RfqBidHistory_processName	= (String)incomingRequest.get("RfqBidHistory_processName");

			if (Utility.isEmpty(RfqBidHistory_processName)) {
				this.setStatus(Status.FAILED);
				throw new TsaException("A process name is necessary to save a bid history list of different sequence");
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