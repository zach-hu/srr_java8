package com.tsa.puridiom.poline.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class PoLineGetCommodity extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String commodityCode = (String) incomingRequest.get("PoLine_commodity");

			result = commodityCode;
			
			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}