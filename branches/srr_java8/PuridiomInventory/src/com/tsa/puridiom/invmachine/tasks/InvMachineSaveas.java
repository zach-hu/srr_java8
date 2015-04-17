package com.tsa.puridiom.invmachine.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvMachineSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invMachine */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvMachine	originalInvMachine = (InvMachine) incomingRequest.get("invMachine");
			InvMachine	invMachine = new InvMachine();

			invMachine.setMachineId(originalInvMachine.getMachineId());
			invMachine.setDescription(originalInvMachine.getDescription());
			invMachine.setWorkCenterId(originalInvMachine.getWorkCenterId());
			invMachine.setAssetId(originalInvMachine.getAssetId());
			invMachine.setNotes(originalInvMachine.getNotes());
			invMachine.setDateEntered(originalInvMachine.getDateEntered());
			invMachine.setDateExpires(originalInvMachine.getDateExpires());
			invMachine.setOwner(originalInvMachine.getOwner());
			invMachine.setStatus(originalInvMachine.getStatus());

			incomingRequest.put("invMachine", invMachine);

			InvMachineAdd addTask = new InvMachineAdd();
			invMachine = (InvMachine) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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