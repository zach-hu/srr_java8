package com.tsa.puridiom.poheader.tasks;
import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class PoHeaderSetEstimatedCost extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if (poHeader == null)
			{
				poHeader = new PoHeader();
			}

			if (incomingRequest.containsKey("PoHeader_icPoHeader"))
			{
				String icPoHeaderString = (String) incomingRequest.get("PoHeader_icPoHeader");
				if (Utility.isEmpty(icPoHeaderString))
				{
					icPoHeaderString = "0";
				}
				BigDecimal icPoHeader = new BigDecimal ( icPoHeaderString );
				poHeader.setIcPoHeader(icPoHeader);
			}
			if (incomingRequest.containsKey("PoHeader_estimatedCost"))
			{
				String estimatedCostString = (String) incomingRequest.get("PoHeader_estimatedCost");
				if (Utility.isEmpty(estimatedCostString))
				{
					estimatedCostString = "0";
				}
				BigDecimal estimatedCost = new BigDecimal (estimatedCostString);
				poHeader.setEstimatedCost(estimatedCost);
			}

			result = poHeader;
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
