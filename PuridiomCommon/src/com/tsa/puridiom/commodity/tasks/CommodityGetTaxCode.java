package com.tsa.puridiom.commodity.tasks;

import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class CommodityGetTaxCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Commodity commodity = (Commodity) incomingRequest.get("commodity");
			if (commodity == null)
			{
				commodity = new Commodity();
			}

			result = commodity.getTaxCode();
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
