package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.List;
import java.util.Map;

public class RfqLineSetListVendor extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqLineList = (List) incomingRequest.get("rfqLineList");

			if (rfqLineList != null)
			{
				for (int i = 0; i < rfqLineList.size(); i++)
				{
					String vendorId = (String) incomingRequest.get("winningVendor"+String.valueOf(i));
					RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
					rfqLine.setVendorAwarded(vendorId);
				}
			}

			result = rfqLineList;
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