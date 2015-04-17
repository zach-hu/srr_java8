package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderAllocateAmount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String result = null;
		try
		{
			PoHeader poh = (PoHeader) incomingRequest.get("poHeader");
			BigDecimal tot = poh.getTotal();
			String allocated = (String) incomingRequest.get("allocatedTotal");
			if (allocated == null)
			{
				allocated = "0";
			}
			BigDecimal alloc = new BigDecimal(allocated);
			result = tot.subtract(alloc).toString();
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}
}
