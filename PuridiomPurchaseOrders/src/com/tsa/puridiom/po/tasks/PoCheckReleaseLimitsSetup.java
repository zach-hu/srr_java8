/**
 * 
 * Created on Feb 13, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoCheckReleaseLimitsSetup.java
 * 
 */
package com.tsa.puridiom.po.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class PoCheckReleaseLimitsSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			PoHeader poh = (PoHeader)incomingRequest.get("poHeader");
			incomingRequest.put("releaseAmount", poh.getTotal());
			incomingRequest.put("releaseLimit", poh.getReleaseLimit());
			incomingRequest.put("poNumber", poh.getPoNumber());
			incomingRequest.put("orderDate", poh.getPoDate());
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
