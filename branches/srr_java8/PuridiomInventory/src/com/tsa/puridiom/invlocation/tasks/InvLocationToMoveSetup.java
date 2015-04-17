/**
 * 
 * Created on Feb 11, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.InvLocationToMoveSetup.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationToMoveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			String itemNumber = (String)incomingRequest.get("InvItem_itemNumber");
			String itemLocation = (String)incomingRequest.get("toLocation");
			incomingRequest.put("InvLocation_itemNumber", itemNumber);
			incomingRequest.put("InvLocation_itemLocation", itemLocation);
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
