/*
 * Created on Jun 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvBinLocationChangeNameSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		
		try
		{
			Map incomingRequest = (Map)object;
			String newLocation = (String)incomingRequest.get("newItemLocation");
			List bins = (List)incomingRequest.get("bins");
			for (int i = 0; i < bins.size(); i++)
			{
				InvBinLocation bin = (InvBinLocation) bins.get(i);
				bin.setItemLocation(newLocation);
				bins.set(i, bin);
			}
			ret = bins;
			
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