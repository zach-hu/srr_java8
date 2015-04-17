package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineSetAutoReleaseQty extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			BigDecimal PoLine_AutoQuantity = (BigDecimal)incomingRequest.get("PoLine_AutoQuantity");
			incomingRequest.put("PoLine_quantity", PoLine_AutoQuantity.toString());
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException("AutoReleased Quantity was not found", e);
		}
		return ret;
	}


}
