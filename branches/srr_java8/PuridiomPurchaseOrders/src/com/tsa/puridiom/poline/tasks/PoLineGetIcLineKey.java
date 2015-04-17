/**
 * Created on Apr 13, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poline.tasks.PoLineGetIcLineKey.java
 *
 */
package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoLineGetIcLineKey extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			PoLine poLine =(PoLine)incomingRequest.get("poLine");
			incomingRequest.put("PoLine_icLineKey", poLine.getIcLineKey().toString());
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return super.executeTask(object);
	}

}
