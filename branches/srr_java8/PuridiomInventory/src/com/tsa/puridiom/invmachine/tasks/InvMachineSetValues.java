package com.tsa.puridiom.invmachine.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InvMachineSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvMachine invMachine = (InvMachine) incomingRequest.get("invMachine");
			if (invMachine == null)
			{
				invMachine = new InvMachine();
			}

			if (incomingRequest.containsKey("InvMachine_machineId"))
			{
				String machineId = (String) incomingRequest.get("InvMachine_machineId");
				invMachine.setMachineId(machineId);
			}
			if (incomingRequest.containsKey("InvMachine_description"))
			{
				String description = (String) incomingRequest.get("InvMachine_description");
				invMachine.setDescription(description);
			}
			if (incomingRequest.containsKey("InvMachine_workCenterId"))
			{
				String workCenterId = (String) incomingRequest.get("InvMachine_workCenterId");
				invMachine.setWorkCenterId(workCenterId);
			}
			if (incomingRequest.containsKey("InvMachine_assetId"))
			{
				String assetId = (String) incomingRequest.get("InvMachine_assetId");
				invMachine.setAssetId(assetId);
			}
			if (incomingRequest.containsKey("InvMachine_notes"))
			{
				String notes = (String) incomingRequest.get("InvMachine_notes");
				invMachine.setNotes(notes);
			}
			if (incomingRequest.containsKey("InvMachine_dateEntered"))
			{
				String dateEnteredString = (String) incomingRequest.get("InvMachine_dateEntered");
				Date dateEntered = Dates.getDate(dateEnteredString);
				invMachine.setDateEntered(dateEntered);
			}
			if (incomingRequest.containsKey("InvMachine_dateExpires"))
			{
				String dateExpiresString = (String) incomingRequest.get("InvMachine_dateExpires");
				Date dateExpires = Dates.getDate(dateExpiresString);
				invMachine.setDateExpires(dateExpires);
			}
			if (incomingRequest.containsKey("InvMachine_owner"))
			{
				String owner = (String) incomingRequest.get("InvMachine_owner");
				invMachine.setOwner(owner);
			}
			if (incomingRequest.containsKey("InvMachine_status"))
			{
				String status = (String) incomingRequest.get("InvMachine_status");
				invMachine.setStatus(status);
			}

			result = invMachine;
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