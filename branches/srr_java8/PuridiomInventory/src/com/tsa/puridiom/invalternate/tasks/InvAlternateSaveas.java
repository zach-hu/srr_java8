package com.tsa.puridiom.invalternate.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import java.util.*;

public class InvAlternateSaveas extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain invAlternate */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			InvAlternate	originalInvAlternate = (InvAlternate) incomingRequest.get("invAlternate");
			InvAlternate	invAlternate = new InvAlternate();

			invAlternate.setIcAlternate(originalInvAlternate.getIcAlternate());
			invAlternate.setItemNumber(originalInvAlternate.getItemNumber());
			invAlternate.setAltItemNumber(originalInvAlternate.getAltItemNumber());
			invAlternate.setDateEntered(originalInvAlternate.getDateEntered());
			invAlternate.setOwner(originalInvAlternate.getOwner());

			incomingRequest.put("invAlternate", invAlternate);

			InvAlternateAdd addTask = new InvAlternateAdd();
			invAlternate = (InvAlternate) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;

			result = invAlternate;
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