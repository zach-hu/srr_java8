package com.tsa.puridiom.poheader.tasks;

import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;

import java.util.List;
import java.util.Map;

public class PoHeaderIdListRetrieveByPoLine extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List poLineListTemp = (List) incomingRequest.get("poLineListTemp");
			List poHeaderIdList = (List) incomingRequest.get("poHeaderIdList");

			for (int x = 0; x < poLineListTemp.size(); x++) {
				PoLine poLine = (PoLine) poLineListTemp.get(x);
				if (!poHeaderIdList.contains(poLine.getIcPoHeader()) && poLine.getPoNumber().compareTo("N/A") != 0) {
					poHeaderIdList.add(poLine.getIcPoHeader());
				}
			}

			result = poHeaderIdList;

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