/**
 * Created on Apr 8, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoGetQtyOnOrder.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvItem;
import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoGetQtyOnOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			InvItem invItem = (InvItem)incomingRequest.get("invItem");
			BigDecimal factor = invItem.getFactor();
			PoLine poLine = (PoLine)incomingRequest.get("invLocation");
			BigDecimal ordered = poLine.getQuantity();
			InvLocation invLocation = (InvLocation)incomingRequest.get("invLocation");
			BigDecimal onOrder = invLocation.getQtyOnOrder();
			
			//TODO need to figure out how to get total qty received
			BigDecimal bdtemp = ordered.multiply(factor);
			onOrder = onOrder.subtract(bdtemp);
			if(onOrder.compareTo(new BigDecimal(0)) < 1)
			{
				onOrder = new BigDecimal(0);
			}
			ret = onOrder;
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

}
