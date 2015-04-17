package com.tsa.puridiom.requisitionline.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class RequisitionLineGetCommodityCode extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			String commodityCode = (String) incomingRequest.get("RequisitionLine_commodityCode");

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