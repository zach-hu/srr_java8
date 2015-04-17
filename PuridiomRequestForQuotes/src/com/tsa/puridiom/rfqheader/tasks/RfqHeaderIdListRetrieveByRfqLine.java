package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.entity.RfqLine;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class RfqHeaderIdListRetrieveByRfqLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqLineListTemp = (List) incomingRequest.get("rfqLineListTemp");
			List rfqHeaderIdList = (List) incomingRequest.get("rfqHeaderIdList");

			for (int x = 0; x < rfqLineListTemp.size(); x++) {
				RfqLine rfqLine = (RfqLine) rfqLineListTemp.get(x);
				if (!rfqHeaderIdList.contains(rfqLine.getIcRfqHeader()) && rfqLine.getRfqNumber().compareTo("N/A") != 0) {
					rfqHeaderIdList.add(rfqLine.getIcRfqHeader());
				}
			}

			result = rfqHeaderIdList;

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result ;
	}
}