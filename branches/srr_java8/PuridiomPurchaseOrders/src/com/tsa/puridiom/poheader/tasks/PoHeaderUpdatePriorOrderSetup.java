/**
 * Created on Mar 15, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderUpdatePriorOrderSetup.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderUpdatePriorOrderSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			List priorOrders = (List)incomingRequest.get("priorOrder");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String prior = poHeader.getLinkPriorOrder();
			List priorOrdersToUpdate = new ArrayList();
			for (Iterator iter = priorOrders.iterator(); iter.hasNext();)
			{
				PoHeader priorOrder = (PoHeader) iter.next();
				String poNumber = priorOrder.getPoNumber();
				if(poNumber.equals(prior))
				{
					priorOrder.setLinkNextOrder(poHeader.getPoNumber());
					priorOrdersToUpdate.add(priorOrder);
				}
			}
			incomingRequest.put("priorOrders", priorOrdersToUpdate);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}
}
