package com.tsa.puridiom.po.tasks;


import java.util.Map;

import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class PoSaveasSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		try
		{
			String poNumber = (String) incomingRequest.get("PoHeader_poNumber");
			incomingRequest.put("newPoHeader_poNumber", poNumber);
			incomingRequest.put("newPoLine_poNumber", poNumber);
			if(incomingRequest.get("newPoHeader_revisionNumber") == null)
			{
				incomingRequest.put("newPoHeader_revisionNumber", "0");
			}
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
