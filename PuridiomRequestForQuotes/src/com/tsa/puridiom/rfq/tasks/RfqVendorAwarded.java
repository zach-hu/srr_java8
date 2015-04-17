package com.tsa.puridiom.rfq.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.RfqHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

public class RfqVendorAwarded extends Task
{

	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object ret = null;
		try
		{
			RfqHeader rfqHeader = (RfqHeader)incomingRequest.get("rfqHeader");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			rfqHeader.setVendorAwarded(poHeader.getVendorId());
			rfqHeader.setAwardDate(Dates.getSqlDate(""));
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, "An Error occurred setting vendor Awarded. " + e.getMessage());
			this.setStatus(Status.FAILED);
		}
		return ret;
	}

}
