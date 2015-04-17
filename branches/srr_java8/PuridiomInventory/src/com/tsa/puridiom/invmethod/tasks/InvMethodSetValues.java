package com.tsa.puridiom.invmethod.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvMethodSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvMethod invMethod = (InvMethod) incomingRequest.get("invMethod");
			if (invMethod == null)
			{
				invMethod = new InvMethod();
			}

			if (incomingRequest.containsKey("InvMethod_methodId"))
			{
				String methodId = (String) incomingRequest.get("InvMethod_methodId");
				invMethod.setMethodId(methodId);
			}
			if (incomingRequest.containsKey("InvMethod_description"))
			{
				String description = (String) incomingRequest.get("InvMethod_description");
				invMethod.setDescription(description);
			}
			if (incomingRequest.containsKey("InvMethod_notes"))
			{
				String notes = (String) incomingRequest.get("InvMethod_notes");
				invMethod.setNotes(notes);
			}
			if (incomingRequest.containsKey("InvMethod_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvMethod_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invMethod.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvMethod_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvMethod_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invMethod.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvMethod_owner"))
			{
				String owner = (String) incomingRequest.get("InvMethod_owner");
				invMethod.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvMethod_status"))
			{
				String status = (String) incomingRequest.get("InvMethod_status");
				invMethod.setStatus(status);
			}

			result = invMethod;
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