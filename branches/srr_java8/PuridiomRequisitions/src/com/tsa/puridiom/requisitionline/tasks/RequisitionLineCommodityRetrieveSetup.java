package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

import java.util.List;
import java.util.Map;

public class RequisitionLineCommodityRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		try
		{
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");

			if (requisitionLine == null)
			{
				List requisitionLineList = (List)incomingRequest.get("requisitionLineList");
				if (requisitionLineList != null && requisitionLineList.size() > 0)
				{
					requisitionLine = (RequisitionLine)requisitionLineList.get(0);
				}
			}

			String commodityCode = "";
			if (requisitionLine != null)
			{
				commodityCode = requisitionLine.getCommodityCode();
			}

			incomingRequest.put("Commodity_commodity", commodityCode);

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
