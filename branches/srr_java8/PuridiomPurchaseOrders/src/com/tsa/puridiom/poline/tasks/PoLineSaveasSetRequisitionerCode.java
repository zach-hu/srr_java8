package com.tsa.puridiom.poline.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;


public class PoLineSaveasSetRequisitionerCode extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest= (Map) object;
		try
		{
			PoLine poLine = (PoLine)incomingRequest.get("poLine");
			if (poLine == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException("PoLine entity was not found!");
			}
			else
			{
				String icHeader = (String)incomingRequest.get("PoLine_icPoHeader");
				String icLine = (String) incomingRequest.get("PoLine_icPoLine");
				String newIcHeader= poLine.getIcPoHeader().toString();
				String newIcLine= poLine.getIcPoLine().toString();

				if (Utility.isEmpty(icHeader))
				{
					this.setStatus(Status.FAILED);
					throw new TsaException("PoLine.icPoHeader can not be empty!");
				}
				else
				{
					poLine.setRequisitionerCode("");
				}
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
