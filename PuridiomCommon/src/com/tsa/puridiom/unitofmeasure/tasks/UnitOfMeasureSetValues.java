package com.tsa.puridiom.unitofmeasure.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.entity.UnitOfMeasure;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Utility;

public class UnitOfMeasureSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			UnitOfMeasure unitOfMeasure = (UnitOfMeasure) incomingRequest.get("unitOfMeasure");
			if (unitOfMeasure == null)
			{
				unitOfMeasure = new UnitOfMeasure();
			}

			if (incomingRequest.containsKey("UnitOfMeasure_umCode"))
			{
				String umCode = (String ) incomingRequest.get("UnitOfMeasure_umCode");
				unitOfMeasure.setUmCode(umCode);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_description"))
			{
				String description = (String ) incomingRequest.get("UnitOfMeasure_description");
				unitOfMeasure.setDescription(description);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_status"))
			{
				String status = (String ) incomingRequest.get("UnitOfMeasure_status");
				unitOfMeasure.setStatus(status);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("UnitOfMeasure_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				unitOfMeasure.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("UnitOfMeasure_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				unitOfMeasure.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_owner"))
			{
				String owner = (String ) incomingRequest.get("UnitOfMeasure_owner");
				unitOfMeasure.setOwner(owner);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_factor"))
			{
				String factorString = (String) incomingRequest.get("UnitOfMeasure_factor");
				if (Utility.isEmpty(factorString))
				{
					factorString = "0";
				}
				BigDecimal factor = new BigDecimal ( factorString );
				unitOfMeasure.setFactor(factor);
			}
			if (incomingRequest.containsKey("UnitOfMeasure_conversion"))
			{
				String conversion = (String ) incomingRequest.get("UnitOfMeasure_conversion");
				unitOfMeasure.setConversion(conversion);
			}

			result = unitOfMeasure;
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