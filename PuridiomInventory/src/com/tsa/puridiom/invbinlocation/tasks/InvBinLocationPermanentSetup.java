/*
 * Created on August 25, 2005
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvBinLocationPermanentSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		
		try
		{
			Map incomingRequest = (Map)object;

			// Set permanent = true so InvBinLocation_tempIc is not used in the retrieve
			incomingRequest.put("permanentOnly", "true");
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}