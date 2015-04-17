package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;

import java.math.BigDecimal;
import java.util.Map;

public class SetupRfqBidHistoryData extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			BigDecimal RfqBidHistory_icRfqHeader = new BigDecimal ((String)incomingRequest.get("RfqHeader_icRfqHeader"));
			BigDecimal RfqBidHistory_icRfqLine = new BigDecimal ((String)incomingRequest.get("RfqLine_icRfqLine"));

			incomingRequest.put("RfqBidHistory_icRfqHeader", RfqBidHistory_icRfqHeader);
			incomingRequest.put("RfqBidHistory_icRfqLine", RfqBidHistory_icRfqLine);


			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}