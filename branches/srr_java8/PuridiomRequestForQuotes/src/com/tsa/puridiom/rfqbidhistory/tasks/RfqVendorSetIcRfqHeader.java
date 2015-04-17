package com.tsa.puridiom.rfqbidhistory.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.*;
import java.util.Map;

public class RfqVendorSetIcRfqHeader extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object ret = null;
		try
		{
			incomingRequest.put("RfqVendor_icRfqHeader",(String) incomingRequest.get("RfqBidHistory_icRfqHeader"));
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