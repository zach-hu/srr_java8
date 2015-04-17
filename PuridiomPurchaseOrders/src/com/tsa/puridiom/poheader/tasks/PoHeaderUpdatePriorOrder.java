/**
 * Created on Mar 17, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderUpdatePrior.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderUpdatePriorOrder extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest =(Map)object;
		try
		{
			List priorOrders = (List)incomingRequest.get("priorOrders");
			for (Iterator iter = priorOrders.iterator(); iter.hasNext();)
			{
				PoHeader prior = (PoHeader) iter.next();
				PoPriorUpdate updateTask = new PoPriorUpdate();
				incomingRequest.put("priorOrder", prior);
				updateTask.executeTask(incomingRequest);
				this.setStatus(updateTask.getStatus());
				if(this.getStatus() != Status.SUCCEEDED)
				{
					throw new TsaException("error updating order: " + prior.getPoNumber());
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}

}
