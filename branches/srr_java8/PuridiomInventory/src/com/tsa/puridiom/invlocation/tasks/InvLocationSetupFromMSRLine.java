package com.tsa.puridiom.invlocation.tasks;

import java.util.Map;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsa.puridiom.entity.RequisitionLine;

public class InvLocationSetupFromMSRLine extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (requisitionLine != null)
			{
				incomingRequest.put("InvLocation_itemNumber", requisitionLine.getItemNumber());
				incomingRequest.put("InvLocation_itemLocation", requisitionLine.getItemLocation());
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