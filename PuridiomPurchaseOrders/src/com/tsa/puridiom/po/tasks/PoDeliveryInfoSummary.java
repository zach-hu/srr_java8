/*
 * Created on Sep 10, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks;.PoDeliveryInfoSummary.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoDeliveryInfoSummary extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		try
		{
			Map incomingRequest = (Map) object;

			BigDecimal orderQty = (BigDecimal) incomingRequest.get("orderQty");
			BigDecimal totalReleased = (BigDecimal) incomingRequest.get("totalReleased");

			if (orderQty.compareTo(totalReleased) < 0)
			{
				incomingRequest.put("balance", "0");
			} else
			{
				incomingRequest.put("balance", (orderQty.subtract(totalReleased)));
			}

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
