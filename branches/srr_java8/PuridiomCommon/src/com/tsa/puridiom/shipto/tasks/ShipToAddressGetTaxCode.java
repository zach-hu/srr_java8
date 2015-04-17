package com.tsa.puridiom.shipto.tasks;

import com.tsa.puridiom.entity.Address;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class ShipToAddressGetTaxCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			Address address = (Address) incomingRequest.get("shipToAddress");
			if (address != null)
			{
			    result = address.getTaxCode();
			}
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
