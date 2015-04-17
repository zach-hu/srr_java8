package com.tsa.puridiom.invmachine.tasks;

import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Dates;
import java.util.Map;

public class InvMachineSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			incomingRequest.put("InvMachine_machineId", "");
			incomingRequest.put("InvMachine_description", "");
			incomingRequest.put("InvMachine_workCenterId", "");
			incomingRequest.put("InvMachine_assetId", "");
			incomingRequest.put("InvMachine_notes", "");
			incomingRequest.put("InvMachine_dateEntered", Dates.today("", ""));
			incomingRequest.put("InvMachine_dateExpires", Dates.today("", ""));
			incomingRequest.put("InvMachine_owner", "");
			incomingRequest.put("InvMachine_status", "");

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