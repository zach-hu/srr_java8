package com.tsa.puridiom.shipto.tasks;

import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ShipToGetShipToCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
		    String shipToCode = (String ) incomingRequest.get("ShipTo_shipToCode");
		    result = shipToCode;
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
