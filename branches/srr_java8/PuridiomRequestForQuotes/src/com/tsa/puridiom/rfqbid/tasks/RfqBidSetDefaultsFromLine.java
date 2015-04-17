package com.tsa.puridiom.rfqbid.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RfqBidSetDefaultsFromLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;
		
		try
		{
			RfqLine rfqLine = (RfqLine) incomingRequest.get("rfqLine");
			if (rfqLine == null)
			{
				rfqLine = new RfqLine();
			}
			
			incomingRequest.put("RfqBid_icRfqHeader", rfqLine.getIcRfqHeader().toString());
			incomingRequest.put("RfqBid_icRfqLine", rfqLine.getIcRfqLine().toString());
			incomingRequest.put("RfqBid_quantity", rfqLine.getQuantity().toString());
			incomingRequest.put("RfqBid_umCode", rfqLine.getUmCode());
			incomingRequest.put("RfqBid_umFactor", rfqLine.getUmFactor().toString());

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