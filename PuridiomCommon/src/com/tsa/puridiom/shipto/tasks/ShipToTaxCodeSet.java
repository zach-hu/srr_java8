package com.tsa.puridiom.shipto.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ShipToTaxCodeSet extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("shipToTaxCodeSet", "Y");
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
