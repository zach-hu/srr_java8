/**
 * Created on Mar 4, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoHeaderGetShipto.java
 *
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderGetShipto extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			incomingRequest.put("ShipTo_icHeader", poHeader.getIcPoHeader().toString());
			incomingRequest.put("ShipTo_icLine", "0");
			
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
