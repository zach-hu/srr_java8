/**
 * Created on Mar 4, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoHeaderGetShipto.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineGetShipto extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			incomingRequest.put("ShipTo_icHeader", poLine.getIcPoHeader().toString());
			incomingRequest.put("ShipTo_icLine", poLine.getIcPoLine().toString());
			
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
