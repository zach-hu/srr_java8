package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.DisbLine;

public class InvLocationSetupFromDisbLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DisbLine disbLine = (DisbLine) incomingRequest.get("disbLine");
			if (disbLine != null)
			{
				incomingRequest.put("InvLocation_itemNumber", disbLine.getItemNumber());
				incomingRequest.put("InvLocation_itemLocation", disbLine.getItemLocation());
			}
			else
			{
				incomingRequest.put("InvLocation_itemNumber", "0");
				incomingRequest.put("InvLocation_itemLocation", "0");
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