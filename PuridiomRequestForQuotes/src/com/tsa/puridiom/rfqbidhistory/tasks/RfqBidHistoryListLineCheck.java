package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.util.ArrayList;
import java.util.Map;

public class RfqBidHistoryListLineCheck extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			Object RfqBidHistory_icRfqLineObj	= incomingRequest.get("RfqBidHistory_icRfqLine");
			String RfqBidHistory_icRfqLine[] = null;

			if (RfqBidHistory_icRfqLineObj instanceof String[]) {
				RfqBidHistory_icRfqLine = (String[]) RfqBidHistory_icRfqLineObj;
			}
			else {
				RfqBidHistory_icRfqLine = new String[1];
				RfqBidHistory_icRfqLine[0] = (String) incomingRequest.get("RfqBidHistory_icRfqLine");
			}

			if (RfqBidHistory_icRfqLine == null || RfqBidHistory_icRfqLine.length == 0) {
				this.setStatus(Status.FAILED);
				throw new TsaException("IcRfqLine list is necessary to retrieve a bid history list of different sequence");
			}
			else {
				incomingRequest.put("RfqBidHistory_icRfqLine", RfqBidHistory_icRfqLine);
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