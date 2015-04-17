package com.tsa.puridiom.poheader.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderAccountRetrieveSetup extends Task
{
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;

		try
		{
			PoHeader poHeader = (PoHeader) incomingRequest.get("poHeader");
			String icHeader = poHeader.getIcPoHeader().toString();
			incomingRequest.put("Account_icHeader", icHeader);
			incomingRequest.put("Account_icLine", "0");

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch(Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return null ;
	}
}