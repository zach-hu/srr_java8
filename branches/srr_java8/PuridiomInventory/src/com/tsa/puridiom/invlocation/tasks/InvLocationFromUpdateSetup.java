/**
 * 
 * Created on Feb 11, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvLocationFromUpdateSetup.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class InvLocationFromUpdateSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			InvLocation location = (InvLocation)incomingRequest.get("fromLocation");
			incomingRequest.put("invLocation", location);
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
