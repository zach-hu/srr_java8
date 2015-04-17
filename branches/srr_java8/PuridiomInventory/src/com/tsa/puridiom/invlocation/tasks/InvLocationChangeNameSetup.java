/*
 * Created on Jun 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationChangeNameSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			String newLocation = (String)incomingRequest.get("newItemLocation");
			String oldLocation = (String)incomingRequest.get("oldItemLocation");
			incomingRequest.put("InvLocation_itemLocation", oldLocation);
			incomingRequest.put("InvBinLocation_itemLocation", oldLocation);
			incomingRequest.put("newItemLocation", newLocation);
			
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
