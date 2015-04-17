/**
 * Created on Feb 19, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoForwardGetPoAmount.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoForwardGetPoAmount extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		BigDecimal poAmount = null;
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String poType = poHeader.getPoType();
			if(poType.equals("BO") || poType.equals("SB") || poType.equals("DO"))
			{
				poAmount = poHeader.getBlanketLimit();
			}
			else
			{
				poAmount = poHeader.getTotal();
			}
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return poAmount;
	}

}
