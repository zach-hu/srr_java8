package com.tsa.puridiom.invbinlocation.tasks;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvBinLocationDataSet extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		InvBinLocation invBinLocation = (InvBinLocation) incomingRequest.get("invBinLocation");

		try
		{
			if (invBinLocation != null) {
				invBinLocation.setInvPropertyList((List)incomingRequest.get("invPropertyList"));
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}

		return invBinLocation;
	}
}
