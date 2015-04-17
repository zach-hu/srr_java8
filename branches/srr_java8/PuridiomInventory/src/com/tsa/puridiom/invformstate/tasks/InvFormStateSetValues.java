package com.tsa.puridiom.invformstate.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.util.Map;

public class InvFormStateSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			InvFormStatePK comp_id = new InvFormStatePK();
			InvFormState invFormState = (InvFormState) incomingRequest.get("invFormState");
			if (invFormState == null)
			{
				invFormState = new InvFormState();
			}

			if (incomingRequest.containsKey("InvFormState_itemNumber"))
			{
				String itemNumber = (String ) incomingRequest.get("InvFormState_itemNumber");
				comp_id.setItemNumber(itemNumber);
			}
			if (incomingRequest.containsKey("InvFormState_stateId"))
			{
				String stateId = (String ) incomingRequest.get("InvFormState_stateId");
				comp_id.setStateId(stateId);
			}
			invFormState.setComp_id(comp_id);

			result = invFormState;
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