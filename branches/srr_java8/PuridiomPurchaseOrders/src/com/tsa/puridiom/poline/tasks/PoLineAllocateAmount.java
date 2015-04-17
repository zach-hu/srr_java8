/*
 * Created on Aug 19, 2003 
 */
package com.tsa.puridiom.poline.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoLineAllocateAmount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String result = null;
		try
		{
			PoLine pol = (PoLine) incomingRequest.get("poLine");
			BigDecimal bdTotal = pol.getLineTotal();
			//BigDecimal discount = pol.getDiscountAmount();
			
			if (bdTotal == null)
			{
				bdTotal = new BigDecimal(0);
			}
			//result = bdTotal.subtract(discount).toString();
			result = bdTotal.toString();
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
