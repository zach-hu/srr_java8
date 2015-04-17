package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.Task;
import java.math.BigDecimal;
import java.util.Map;

public class RfqLineSetDefaultsFromHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		
		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			if (rfqHeader == null)
			{
				rfqHeader = new RfqHeader();
				if (incomingRequest.containsKey("RfqHeader_icRfqHeader")) {
					String	icRfqHeaderString = (String) incomingRequest.get("RfqHeader_icRfqHeader");
					rfqHeader.setIcRfqHeader(new BigDecimal(icRfqHeaderString));
				}
				if (incomingRequest.containsKey("RfqHeader_rfqNumber")) {
					String	rfqNumber = (String) incomingRequest.get("RfqHeader_rfqNumber");
					rfqHeader.setRfqNumber(rfqNumber);
				}
			}
			
			incomingRequest.put("RfqLine_icRfqHeader", String.valueOf(rfqHeader.getIcRfqHeader()));
			incomingRequest.put("RfqLine_rfqNumber", rfqHeader.getRfqNumber());
		}
		catch (Exception e)
		{
			throw e;
		}
		
		return null;
	}
}