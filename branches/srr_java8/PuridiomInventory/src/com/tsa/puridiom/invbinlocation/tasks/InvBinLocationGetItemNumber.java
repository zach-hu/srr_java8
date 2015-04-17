package com.tsa.puridiom.invbinlocation.tasks;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvBinLocationGetItemNumber extends Task{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvBinLocation binLocation = (InvBinLocation) incomingRequest.get("invBinLocation");

			result = binLocation.getItemNumber();
			this.setStatus(Status.COMPLETED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}