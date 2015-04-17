package com.tsa.puridiom.requisitionheader.tasks;
import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RequisitionHeaderSetEstimatedCost extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		String organizationId = (String) incomingRequest.get("organizationId");
		try
		{
			RequisitionHeader requisitionHeader = (RequisitionHeader)incomingRequest.get("requisitionHeader");
			if (requisitionHeader == null)
			{
				requisitionHeader = new RequisitionHeader();
			}

			if (incomingRequest.containsKey("RequisitionHeader_icReqHeader"))
			{
				String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
				if (Utility.isEmpty(icReqHeaderString))
				{
					icReqHeaderString = "0";
				}
				BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
				requisitionHeader.setIcReqHeader(icReqHeader);
			}
			if (incomingRequest.containsKey("RequisitionHeader_estimatedCost"))
			{
				String estimatedCostString = (String) incomingRequest.get("RequisitionHeader_estimatedCost");
				if (Utility.isEmpty(estimatedCostString))
				{
					estimatedCostString = "0";
				}
				BigDecimal estimatedCost = new BigDecimal (estimatedCostString);
				requisitionHeader.setEstimatedCost(estimatedCost);
			}

			result = requisitionHeader;
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
