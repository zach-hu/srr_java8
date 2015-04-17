package com.tsa.puridiom.bommethod.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomMethodSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			BomMethod bomMethod = (BomMethod) incomingRequest.get("bomMethod");
			if (bomMethod == null)
			{
				bomMethod = new BomMethod();
			}

			if (incomingRequest.containsKey("BomMethod_icMethod"))
			{
				String icMethodString = (String) incomingRequest.get("BomMethod_icMethod");
				if (Utility.isEmpty(icMethodString))
				{
					icMethodString = "0";
				}
				BigDecimal icMethod = new BigDecimal ( icMethodString );
				bomMethod.setIcMethod(icMethod);
			}
			if (incomingRequest.containsKey("BomMethod_parentItem"))
			{
				String parentItem = (String) incomingRequest.get("BomMethod_parentItem");
				bomMethod.setParentItem(parentItem);
			}
			if (incomingRequest.containsKey("BomMethod_componentItem"))
			{
				String componentItem = (String) incomingRequest.get("BomMethod_componentItem");
				bomMethod.setComponentItem(componentItem);
			}
			if (incomingRequest.containsKey("BomMethod_methodId"))
			{
				String methodId = (String) incomingRequest.get("BomMethod_methodId");
				bomMethod.setMethodId(methodId);
			}
			if (incomingRequest.containsKey("BomMethod_batchSize"))
			{
				String batchSizeString = (String) incomingRequest.get("BomMethod_batchSize");
				if (Utility.isEmpty(batchSizeString))
				{
					batchSizeString = "0";
				}
				BigDecimal batchSize = new BigDecimal ( batchSizeString );
				bomMethod.setBatchSize(batchSize);
			}
			if (incomingRequest.containsKey("BomMethod_unitOfMeasure"))
			{
				String unitOfMeasure = (String) incomingRequest.get("BomMethod_unitOfMeasure");
				bomMethod.setUnitOfMeasure(unitOfMeasure);
			}
			if (incomingRequest.containsKey("BomMethod_description"))
			{
				String description = (String) incomingRequest.get("BomMethod_description");
				bomMethod.setDescription(description);
			}
			if (incomingRequest.containsKey("BomMethod_notes"))
			{
				String notes = (String) incomingRequest.get("BomMethod_notes");
				bomMethod.setNotes(notes);
			}
			if (incomingRequest.containsKey("BomMethod_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("BomMethod_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				bomMethod.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("BomMethod_owner"))
			{
				String owner = (String) incomingRequest.get("BomMethod_owner");
				bomMethod.setOwner(owner);
			}

			result = bomMethod;
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