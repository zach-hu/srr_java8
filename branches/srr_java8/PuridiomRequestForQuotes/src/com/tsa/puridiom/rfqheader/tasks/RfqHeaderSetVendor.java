package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;

import java.util.Map;
import java.util.Date;

public class RfqHeaderSetVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RfqHeader rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			String vendorId = (String) incomingRequest.get("awardSelectionType");

			if (vendorId.compareTo("byItem") == 0) {
				vendorId = "MULTIPLE";
			}
			rfqHeader.setVendorAwarded(vendorId);
			rfqHeader.setAwardDate(new Date());

			result = rfqHeader;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}