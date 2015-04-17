package com.tsa.puridiom.requisition.rules;

import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class RequisitionValidBeforeSourcing extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionHeader header = (RequisitionHeader)incomingRequest.get("header");
			List<RequisitionLine> lineItems = (List<RequisitionLine>)incomingRequest.get("lineitems");
			boolean engineerReview = false;
			boolean validSourcingValues = true;
			boolean validationRequisitionLine = false;
			if(incomingRequest.containsKey("valType") && ((String)incomingRequest.get("valType")).equals("REQUISITIONLINE"))
			{
				validationRequisitionLine = true;
			}
			if (header.getStatus().equals("0535"))
			{
				if(!validationRequisitionLine)
				{
					if (header.getPriorityCode().equalsIgnoreCase("ER") || header.getUdf7Code().equalsIgnoreCase("ER"))
					{
						engineerReview = true;
					}
				}

				for (int i = 0; i < lineItems.size(); i++)
				{
					if (lineItems.get(i).getUdf2Code().equalsIgnoreCase("ER") || lineItems.get(i).getUdf3Code().equalsIgnoreCase("ER"))
					{
						engineerReview = true;
					}
				}

				if (engineerReview)
				{
					validSourcingValues = false;
				}
			}

			incomingRequest.put("validSourcingValues", String.valueOf(validSourcingValues));

		}
		catch (Exception e)
		{
			Log.error("Could not validate", e.getMessage());
			throw e;
		}

		return result;
	}
}
