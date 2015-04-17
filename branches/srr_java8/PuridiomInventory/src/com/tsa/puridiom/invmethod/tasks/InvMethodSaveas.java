package com.tsa.puridiom.invmethod.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvMethodSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invMethod */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvMethod	originalInvMethod = (InvMethod) incomingRequest.get("invMethod");
			InvMethod	invMethod = new InvMethod();

			invMethod.setMethodId(originalInvMethod.getMethodId());
			invMethod.setDescription(originalInvMethod.getDescription());
			invMethod.setNotes(originalInvMethod.getNotes());
			invMethod.setDateEntered(originalInvMethod.getDateEntered());
			invMethod.setDateExpires(originalInvMethod.getDateExpires());
			invMethod.setOwner(originalInvMethod.getOwner());
			invMethod.setStatus(originalInvMethod.getStatus());

			incomingRequest.put("invMethod", invMethod);

			InvMethodAdd addTask = new InvMethodAdd();
			invMethod = (InvMethod) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

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