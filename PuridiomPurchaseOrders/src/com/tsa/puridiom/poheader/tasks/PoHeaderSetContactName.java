/**
 * 
 * Created on Feb 2, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderSetContactName.java
 * 
 */
package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoHeaderSetContactName extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
		try
		{
			poHeader.setContactName((String)incomingRequest.get("PoHeader_contactName"));
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(e.toString(), e);
		}
		return poHeader;
	}

}
